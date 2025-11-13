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

public class aK {
   public static boolean pC(Map var0, boolean var1, boolean var2) {
      boolean var3 = false;
      ArrayList var4 = new ArrayList();
      int var5 = 0;

      for (Entry var7 : var0.entrySet()) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var8 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.getValue();
         String var9 = var8.wS().pC();
         if (var9.startsWith("PUSH")) {
            var3 = true;
         } else if (var1 && !var3 && var9.startsWith("POP")) {
            return false;
         }

         if (var8.getProcessorMode() == 16 && var1 && !var2) {
            if (var9.equals("IT")) {
               var5 = uV.A(var8.kS()).size() + 1;
            } else {
               if (!var8.pC().isPCUpdated() && !var8.ld() && !Strings.isContainedIn(var9, "HVC", "SMC", "SVC")) {
                  ArrayList var10 = new ArrayList();
                  ArrayList var11 = new ArrayList();
                  var8.getDefUse(var10, var11);
                  var4.removeAll(var11);
                  if (!var8.wS().A() && var8.UT().E() && var5 == 0 && !var9.equals("POP")) {
                     if (var10.size() > 1) {
                        var10.clear();
                     } else {
                        mN var12 = MX.pC(var8);
                        if (var12 != null && var12.wS().UT()) {
                           Yg var13 = var12.wS().A();
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

   public static boolean pC(INativeCodeAnalyzer var0, long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3) {
      return yx.pC(var0, var1, var3);
   }

   public static boolean pC(INativeCodeAnalyzer var0, long var1) {
      return yx.pC(var0, var1);
   }

   public static boolean pC(IVirtualMemory var0, long var1) {
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
