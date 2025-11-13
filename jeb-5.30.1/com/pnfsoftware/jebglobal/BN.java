package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class BN {
   public static boolean q(Map var0, boolean var1, boolean var2) {
      boolean var3 = false;
      ArrayList var4 = new ArrayList();
      int var5 = 0;

      for (Entry var7 : var0.entrySet()) {
         fA var8 = (fA)var7.getValue();
         String var9 = var8.Dw().q();
         if (var9.startsWith("PUSH")) {
            var3 = true;
         } else if (var1 && !var3 && var9.startsWith("POP")) {
            return false;
         }

         if (var8.getProcessorMode() == 16 && var1 && !var2) {
            if (var9.equals("IT")) {
               var5 = Lf.RF(var8.xK()).size() + 1;
            } else {
               if (!var8.q().isPCUpdated() && !var8.gP() && !Strings.isContainedIn(var9, "HVC", "SMC", "SVC")) {
                  ArrayList var10 = new ArrayList();
                  ArrayList var11 = new ArrayList();
                  var8.getDefUse(var10, var11);
                  var4.removeAll(var11);
                  if (!var8.Dw().RF() && var8.Uv().oW() && var5 == 0 && !var9.equals("POP")) {
                     if (var10.size() > 1) {
                        var10.clear();
                     } else {
                        fV var12 = PG.q(var8);
                        if (var12 != null && var12.Dw().Uv()) {
                           CW var13 = var12.Dw().RF();
                           var10.remove(Integer.valueOf((int)RegisterUtil.getPureId(var13.getOperandValue())));
                        }
                     }

                     var10.remove(Integer.valueOf(12));
                     var10.remove(Integer.valueOf(13));
                     if (CollectionUtil.hasIntersection(var4, var10)) {
                        return false;
                     }

                     var4.addAll(var10);
                  }
               } else {
                  var4.clear();
               }

               if (var5 > 0) {
                  var5--;
               }
            }
         }
      }

      return true;
   }

   public static boolean q(INativeCodeAnalyzer var0, long var1, fA var3) {
      return Ql.q(var0, var1, var3);
   }

   public static boolean q(INativeCodeAnalyzer var0, long var1) {
      return Ql.q(var0, var1);
   }

   public static boolean q(IVirtualMemory var0, long var1) {
      long var3 = var1 & -4L;

      int var5;
      try {
         var5 = var0.readInt(var3);
      } catch (MemoryException var6) {
         return false;
      }

      return (var5 & -268435456) == -536870912;
   }
}
