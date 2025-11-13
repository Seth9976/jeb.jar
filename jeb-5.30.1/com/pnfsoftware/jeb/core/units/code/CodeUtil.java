package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.android.DalvikCallgraphBuilder;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.asm.NativeCallgraphBuilder;
import com.pnfsoftware.jeb.util.graph.IAddressableDigraphBuilder;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CodeUtil {
   private static final ILogger logger = GlobalLog.getLogger(CodeUtil.class);

   public static String findCodeItemReference(ICodeUnit var0, ICodeItem var1, int var2) {
      if (var2 < 0) {
         return null;
      } else {
         String var3 = var1.getAddress();
         if (var3 != null && var2 == 0) {
            return var3;
         } else {
            long var4 = var1.getItemId();
            if (var4 != 0L) {
               ActionContext var6 = new ActionContext(var0, 4, var4);
               ActionXrefsData var7 = new ActionXrefsData();
               if (var0.prepareExecution(var6, var7)) {
                  List var8 = var7.getAddresses();
                  if (var8 != null && var2 < var8.size()) {
                     var3 = (String)var8.get(var2);
                  }
               }
            }

            return var3;
         }
      }
   }

   public static ICodeMethod getMethodByName(ICodeUnit var0, String var1, boolean var2) {
      ICodeMethod var3 = null;
      ArrayList var4 = new ArrayList();

      for (ICodeMethod var6 : var0.getMethods()) {
         String var7 = var6.getName(true);
         if (var7 != null) {
            if (var7.equals(var1)) {
               if (var3 != null) {
                  logger.error(S.L("Unit '%s': Two or more methods have the name '%s'"), var0, var7);
                  return null;
               }

               var3 = var6;
            } else if (var7.contains(var1)) {
               var4.add(var6);
            }
         }
      }

      if (var3 == null && !var4.isEmpty()) {
         if (var4.size() >= 2 && var2) {
            return null;
         }

         var3 = (ICodeMethod)var4.get(0);
      }

      return var3;
   }

   public static ICodeField getFieldByName(ICodeUnit var0, String var1, boolean var2) {
      ICodeField var3 = null;
      ArrayList var4 = new ArrayList();

      for (ICodeField var6 : var0.getFields()) {
         String var7 = var6.getName(true);
         if (var7 != null) {
            if (var7.equals(var1)) {
               if (var3 != null) {
                  logger.error(S.L("Unit '%s': Two or more fields have the name '%s'"), var0, var7);
                  return null;
               }

               var3 = var6;
            } else if (var7.contains(var1)) {
               var4.add(var6);
            }
         }
      }

      if (var3 == null && !var4.isEmpty()) {
         if (var4.size() >= 2 && var2) {
            return null;
         }

         var3 = (ICodeField)var4.get(0);
      }

      return var3;
   }

   @Deprecated
   public static List collectClasses(ICodeUnit var0, boolean var1, boolean var2, Pattern var3) {
      return collectClasses(var0, var1, var2, var3, true);
   }

   public static List collectClasses(ICodeUnit var0, boolean var1, boolean var2, Pattern var3, boolean var4) {
      ArrayList var5 = new ArrayList();

      for (ICodeClass var7 : var0.getClasses()) {
         if ((!var1 || var7.isInternal()) && (!var2 || (var7.getGenericFlags() & 1048576) == 0)) {
            String var8 = var7.getAddress();
            if (var3 == null || (!var4 || var3.matcher(var8).matches()) && (var4 || var3.matcher(var8).find())) {
               var5.add(var7);
            }
         }
      }

      return var5;
   }

   @Deprecated
   public static List collectMethods(ICodeUnit var0, boolean var1, boolean var2, Pattern var3) {
      return collectMethods(var0, var1, var2, var3, true);
   }

   public static List collectMethods(ICodeUnit var0, boolean var1, boolean var2, Pattern var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      List var6;
      if (var0 instanceof INativeCodeUnit) {
         var6 = ((INativeCodeUnit)var0).getInternalMethodsLeafFirst();
      } else {
         var6 = var0.getMethods();
      }

      for (ICodeMethod var8 : var6) {
         if ((!var1 || var8.isInternal()) && (!var2 || var8.getClassType() == null)) {
            String var9 = var8.getAddress();
            if (var3 == null || (!var4 || var3.matcher(var9).matches()) && (var4 || var3.matcher(var9).find())) {
               var5.add(var8);
            }
         }
      }

      return var5;
   }

   public static IAddressableDigraphBuilder createCallgraphBuilder(ICodeUnit var0) {
      if (var0 instanceof IDexUnit) {
         return new DalvikCallgraphBuilder((IDexUnit)var0);
      } else {
         return var0 instanceof INativeCodeUnit ? new NativeCallgraphBuilder((INativeCodeUnit)var0) : null;
      }
   }
}
