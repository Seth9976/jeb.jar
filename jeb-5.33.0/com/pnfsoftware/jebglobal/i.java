package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class i {
   private static final ILogger A = GlobalLog.getLogger(i.class);
   private static boolean kS = false;
   private CW wS;
   private INativeCodeAnalyzer UT;
   private com.pnfsoftware.jeb.corei.parsers.arm.Av E;
   private MemoryRanges sY;
   private IVirtualMemory ys;
   private IProcessor ld;
   int pC;

   public i(CW var1, INativeCodeAnalyzer var2, com.pnfsoftware.jeb.corei.parsers.arm.Av var3, int var4) {
      this.UT = var2;
      this.E = var3;
      this.wS = var1;
      this.sY = var2.getAnalysisRanges();
      this.ys = var2.getMemory();
      this.ld = var2.getProcessor();
      this.pC = var4;
   }

   public boolean pC(long var1, long var3) {
      int var5 = -1;
      byte var6 = 0;
      int var7 = 256;
      if (this.wS.kS() instanceof CW.Sv && this.wS.kS().pC() > 5) {
         var7 = this.wS.kS().pC();
      }

      HashSet var8 = new HashSet();
      HashSet var9 = new HashSet();
      var8.add(var3);
      var8.add(var1);
      HashMap var10 = new HashMap();
      i.Av var11 = i.Av.pC;
      if (EUtil.countVariableUse(this.wS.A().A) == 0) {
         var5 = 1;
         var11 = i.Av.wS;
      } else if (this.wS.kS() instanceof CW.Av && ((CW.Av)this.wS.kS()).pC) {
         if (this.wS.pC()) {
            Object[] var24 = new Object[]{((CW.Av)this.wS.kS()).A.get(0)};
            return true;
         }

         var11 = i.Av.A;
      }

      if (var11 != i.Av.wS && this.wS.A().wS()) {
         Object[] var20 = new Object[]{this.wS.A().pC};
         var20 = new Object[]{this.wS.A().A};
         var5 = 0;
         Long[] var16 = new Long[]{this.wS.A().pC(this.ys, 0), null};
         if (var16[0] == null) {
            return false;
         }

         while (var5 < var7) {
            var16[1] = this.wS.A().pC(this.ys, var5);
            if (var16[1] == null || !this.sY.contains(var16[1])) {
               break;
            }

            long var13 = this.wS.A().A(this.ys, var5);
            if (var5 == 0) {
               if (!this.sY.contains(var13)) {
                  var20 = new Object[]{var13};
                  var6 = 1;
               } else if (this.pC(var13)) {
                  var20 = new Object[]{var13};
                  var6 = 1;
               } else {
                  this.pC(var8, var9, var10, var5, -1, var16, var13, var1, var11);
               }
            } else {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var15 = PU.pC(this.ld, this.ys, var16[1], this.pC);
               if (var15 != null && var15.wS().pC().equals("NOP") || !this.pC(var8, var9, var10, var5, var7, var16, var13, var1, var11)) {
                  break;
               }
            }

            var5++;
            if (var5 > 10 && var8.size() == 2 && var9.isEmpty()) {
               var5 = var6 == 1 ? 2 : 1;
               break;
            }
         }

         if (!var10.isEmpty()) {
            while (var10.containsKey(var5 - 1)) {
               var5--;
            }
         }
      } else if (var11 != i.Av.wS && this.wS.A().UT()) {
         for (var5 = 0; var5 < var7; var5++) {
            long var12 = this.wS.A().A(this.ys, var5);
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var14 = PU.pC(this.ld, this.ys, var12, this.pC);
            if (var5 == 0) {
               if (var14 == null || !this.sY.contains(var12)) {
                  Object[] var19 = new Object[]{var12};
                  var6 = 1;
               } else if (this.pC(var12)) {
                  Object[] var10000 = new Object[]{var12};
                  var6 = 1;
               }
            } else if (var14 == null || var14.wS().pC().equals("NOP") || !this.sY.contains(var12) || this.pC(var12)) {
               break;
            }
         }
      }

      if (var5 == 1) {
         Long var17 = this.wS.A().pC(this.ys, 0);
         if (var17 == null) {
            return false;
         }

         long var18 = this.wS.A().A(this.ys, 0);
         if (!this.pC(var8, var9, null, 0, -1, new Long[]{var17, var17}, var18, var1, i.Av.pC)) {
            return false;
         }
      }

      if (var6 != var5 && var5 > 0) {
         this.wS.pC(var6, var5);
         return true;
      } else if (EUtil.countVariableUse(this.wS.A().A) == 0) {
         this.wS.pC(0L);
         return true;
      } else {
         return this.wS.kS() instanceof CW.Av && !((CW.Av)this.wS.kS()).A.isEmpty();
      }
   }

   private boolean pC(Set var1, Set var2, Map var3, int var4, int var5, Long[] var6, long var7, long var9, i.Av var11) {
      if (this.pC == 64 && (var7 & 3L) != 0L) {
         return false;
      } else if (var7 >= var6[0] && var7 <= var6[1]) {
         return false;
      } else if (!var1.contains(var6[1]) && !var1.contains(var6[1] + 1L)) {
         int var12 = this.pC == 64 ? 36 : 16;
         if (var7 > 0L && this.sY.contains(var7) && var9 != var7 && var9 + 1L != var7) {
            int var13 = 16 + var4 * 8;
            if (var6[1] < var9 + var13 && var6[1] > var9 - var13) {
               Object[] var26 = new Object[]{var4, var6[1], var9, var7};
               var12 = 65536;
            } else {
               Object[] var10000 = new Object[]{var4, var6[1], var9};
            }

            if (this.pC(var7)) {
               Object[] var27 = new Object[]{var7};
               boolean var14 = false;
               byte var15 = 2;

               for (int var16 = 0; var16 < var15 && var4 + 1 < var5; var16++) {
                  Long var17 = this.wS.A().pC(this.ys, var4 + 1 + var15);
                  if (var17 == null || !this.sY.contains(var17)) {
                     break;
                  }

                  var14 = var1.contains(this.wS.A().A(this.ys, var4 + 1 + var15));
                  if (var14) {
                     break;
                  }
               }

               if (var3 != null) {
                  var3.clear();
               }

               if (!var14) {
                  return false;
               }
            }

            for (Long var21 : var1) {
               if (var7 < var21 + var12 && var7 > var21 - var12) {
                  Object[] var28 = new Object[]{var7, var21};
                  this.pC(var1, var7);
                  if (var3 != null) {
                     ArrayList var22 = new ArrayList();

                     for (Entry var18 : var3.entrySet()) {
                        if ((Long)var18.getValue() < var7 + var12 && (Long)var18.getValue() > var7 - var12) {
                           var22.add((Integer)var18.getKey());
                        }
                     }

                     for (Integer var25 : var22) {
                        Long var19 = (Long)var3.remove(var25);
                        this.pC(var1, var19);
                     }
                  }

                  return true;
               }
            }

            Object[] var29 = new Object[]{var7};
            if (var3 != null) {
               if (this.A(var7)) {
                  var2.add(var7);
                  return true;
               }

               var3.put(var4, var7);
               if (var3.size() < 5) {
                  return true;
               }

               if (this.wS.kS().pC() != 256 && var3.size() < 16) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void pC(Set var1, long var2) {
      int var4 = this.pC;
      if ((var2 & 1L) != 0L) {
         var4 = 16;
         var2--;
      }

      long var5 = var2;
      var1.add(var2);
      int var8 = 0;

      com.pnfsoftware.jeb.corei.parsers.arm.rQ var7;
      do {
         var7 = PU.pC(this.ld, this.ys, var5 + var8, var4);
         if (var7 == null) {
            break;
         }

         var8 += var7.getSize();
         if (var8 >= 30) {
            var5 += var8;
            var8 = 0;
            var1.add(var5);
         }
      } while (!var7.pC().isPCUpdated() && var5 + var8 - var2 <= 128L);

      if (var8 > 0) {
         var1.add(var5 + var8);
      }
   }

   private boolean pC(long var1) {
      if (this.pC == 64) {
         if (kS) {
            Long var3 = VirtualMemoryUtil.readAsLongSafe(this.ys, var1 - 4L, 4);
            Long var4 = VirtualMemoryUtil.readAsLongSafe(this.ys, var1, 4);
            if (var3 != null && var3.intValue() == 0L || var4 != null && var4 == 0L) {
               return false;
            }
         }

         com.pnfsoftware.jeb.corei.parsers.arm.rQ var5 = PU.pC(this.UT, var1 - 4L);
         if (var5 != null && !PU.E(var5) && !this.E.E(var1)) {
            return true;
         }
      }

      return false;
   }

   private boolean A(long var1) {
      if (this.pC == 64) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var3 = PU.pC(this.UT, var1 - 4L);
         if (var3 != null && var3.wS().pC().equals("RET") && this.E.E(var1)) {
            return true;
         }
      }

      return false;
   }

   private static enum Av {
      pC,
      A,
      kS,
      wS;
   }
}
