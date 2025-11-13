package com.pnfsoftware.jeb.core.util;

import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import java.util.ArrayList;
import java.util.List;

public class DebuggerHelper {
   public static String typeName(String var0) {
      return "dbug_" + var0;
   }

   public static IDebuggerUnit getDebugger(IUnit var0) {
      return getDebugger(var0, true);
   }

   public static IDebuggerUnit getDebugger(IUnit var0, boolean var1) {
      if (var0 == null) {
         return null;
      } else {
         IRuntimeProject var2 = RuntimeProjectUtil.findProject(var0);
         List var3 = RuntimeProjectUtil.findUnitsByType(var2, IDebuggerUnit.class, false);
         ArrayList var4 = new ArrayList();

         for (IDebuggerUnit var6 : var3) {
            if (var6.getTargetApplication() == var0) {
               var4.add(var6);
            }
         }

         if (var4.size() == 1) {
            return (IDebuggerUnit)var4.get(0);
         } else {
            for (IDebuggerUnit var12 : var4) {
               if (var12.isAttached()) {
                  return var12;
               }
            }

            if (var4.size() > 0) {
               return (IDebuggerUnit)var4.get(0);
            } else {
               var4 = new ArrayList();

               for (IDebuggerUnit var13 : var3) {
                  if (var13.getPotentialDebuggees().contains(var0)) {
                     var4.add(var13);
                  }
               }

               if (var4.size() == 1) {
                  return (IDebuggerUnit)var4.get(0);
               } else {
                  for (IDebuggerUnit var14 : var4) {
                     if (var14.isAttached()) {
                        return var14;
                     }
                  }

                  if (var4.size() > 0) {
                     return (IDebuggerUnit)var4.get(0);
                  } else if (!var1) {
                     return null;
                  } else {
                     IDebuggerUnit var11;
                     for (var11 = null; var0 != null; var0 = (IUnit)var0.getParent()) {
                        var11 = var0.getUnitProcessor().createDebugger("", var0);
                        if (var11 != null || !(var0.getParent() instanceof IUnit)) {
                           break;
                        }
                     }

                     return var11;
                  }
               }
            }
         }
      }
   }
}
