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

public class hu {
   private static final ILogger RF = GlobalLog.getLogger(hu.class);
   private static final int xK = 5;
   private static final int Dw = 16;
   private static final int Uv = 256;
   private static boolean oW = false;
   private Vl gO;
   private INativeCodeAnalyzer nf;
   private FS gP;
   private MemoryRanges za;
   private IVirtualMemory lm;
   private IProcessor zz;
   int q;

   public hu(Vl var1, INativeCodeAnalyzer var2, FS var3, int var4) {
      this.nf = var2;
      this.gP = var3;
      this.gO = var1;
      this.za = var2.getAnalysisRanges();
      this.lm = var2.getMemory();
      this.zz = var2.getProcessor();
      this.q = var4;
   }

   public boolean q(long var1, long var3) {
      int var5 = -1;
      byte var6 = 0;
      int var7 = 256;
      if (this.gO.xK() instanceof Vl.CU && this.gO.xK().q() > 5) {
         var7 = this.gO.xK().q();
      }

      HashSet var8 = new HashSet();
      HashSet var9 = new HashSet();
      var8.add(var3);
      var8.add(var1);
      HashMap var10 = new HashMap();
      hu.eo var11 = hu.eo.q;
      if (EUtil.countVariableUse(this.gO.RF().RF) == 0) {
         var5 = 1;
         var11 = hu.eo.Dw;
      } else if (this.gO.xK() instanceof Vl.eo && ((Vl.eo)this.gO.xK()).q) {
         if (this.gO.q()) {
            Object[] var24 = new Object[]{((Vl.eo)this.gO.xK()).RF.get(0)};
            return true;
         }

         var11 = hu.eo.RF;
      }

      if (var11 != hu.eo.Dw && this.gO.RF().Dw()) {
         Object[] var20 = new Object[]{this.gO.RF().q};
         var20 = new Object[]{this.gO.RF().RF};
         var5 = 0;
         Long[] var16 = new Long[]{this.gO.RF().q(this.lm, 0), null};
         if (var16[0] == null) {
            return false;
         }

         while (var5 < var7) {
            var16[1] = this.gO.RF().q(this.lm, var5);
            if (var16[1] == null || !this.za.contains(var16[1])) {
               break;
            }

            long var13 = this.gO.RF().RF(this.lm, var5);
            if (var5 == 0) {
               if (!this.za.contains(var13)) {
                  var20 = new Object[]{var13};
                  var6 = 1;
               } else if (this.q(var13)) {
                  var20 = new Object[]{var13};
                  var6 = 1;
               } else {
                  this.q(var8, var9, var10, var5, -1, var16, var13, var1, var11);
               }
            } else {
               fA var15 = OC.q(this.zz, this.lm, var16[1], this.q);
               if (var15 != null && var15.Dw().q().equals("NOP") || !this.q(var8, var9, var10, var5, var7, var16, var13, var1, var11)) {
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
      } else if (var11 != hu.eo.Dw && this.gO.RF().Uv()) {
         for (var5 = 0; var5 < var7; var5++) {
            long var12 = this.gO.RF().RF(this.lm, var5);
            fA var14 = OC.q(this.zz, this.lm, var12, this.q);
            if (var5 == 0) {
               if (var14 == null || !this.za.contains(var12)) {
                  Object[] var19 = new Object[]{var12};
                  var6 = 1;
               } else if (this.q(var12)) {
                  Object[] var10000 = new Object[]{var12};
                  var6 = 1;
               }
            } else if (var14 == null || var14.Dw().q().equals("NOP") || !this.za.contains(var12) || this.q(var12)) {
               break;
            }
         }
      }

      if (var5 == 1) {
         Long var17 = this.gO.RF().q(this.lm, 0);
         if (var17 == null) {
            return false;
         }

         long var18 = this.gO.RF().RF(this.lm, 0);
         if (!this.q(var8, var9, null, 0, -1, new Long[]{var17, var17}, var18, var1, hu.eo.q)) {
            return false;
         }
      }

      if (var6 != var5 && var5 > 0) {
         this.gO.q(var6, var5);
         return true;
      } else if (EUtil.countVariableUse(this.gO.RF().RF) == 0) {
         this.gO.q(0L);
         return true;
      } else {
         return this.gO.xK() instanceof Vl.eo && !((Vl.eo)this.gO.xK()).RF.isEmpty();
      }
   }

   private boolean q(Set var1, Set var2, Map var3, int var4, int var5, Long[] var6, long var7, long var9, hu.eo var11) {
      if (this.q == 64 && (var7 & 3L) != 0L) {
         return false;
      } else if (var7 >= var6[0] && var7 <= var6[1]) {
         return false;
      } else if (!var1.contains(var6[1]) && !var1.contains(var6[1] + 1L)) {
         int var12 = this.q == 64 ? 36 : 16;
         if (var7 > 0L && this.za.contains(var7) && var9 != var7 && var9 + 1L != var7) {
            int var13 = 16 + var4 * 8;
            if (var6[1] < var9 + var13 && var6[1] > var9 - var13) {
               Object[] var26 = new Object[]{var4, var6[1], var9, var7};
               var12 = 65536;
            } else {
               Object[] var10000 = new Object[]{var4, var6[1], var9};
            }

            if (this.q(var7)) {
               Object[] var27 = new Object[]{var7};
               boolean var14 = false;
               byte var15 = 2;

               for (int var16 = 0; var16 < var15 && var4 + 1 < var5; var16++) {
                  Long var17 = this.gO.RF().q(this.lm, var4 + 1 + var15);
                  if (var17 == null || !this.za.contains(var17)) {
                     break;
                  }

                  var14 = var1.contains(this.gO.RF().RF(this.lm, var4 + 1 + var15));
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
                  this.q(var1, var7);
                  if (var3 != null) {
                     ArrayList var22 = new ArrayList();

                     for (Entry var18 : var3.entrySet()) {
                        if ((Long)var18.getValue() < var7 + var12 && (Long)var18.getValue() > var7 - var12) {
                           var22.add((Integer)var18.getKey());
                        }
                     }

                     for (Integer var25 : var22) {
                        Long var19 = (Long)var3.remove(var25);
                        this.q(var1, var19);
                     }
                  }

                  return true;
               }
            }

            Object[] var29 = new Object[]{var7};
            if (var3 != null) {
               if (this.RF(var7)) {
                  var2.add(var7);
                  return true;
               }

               var3.put(var4, var7);
               if (var3.size() < 5) {
                  return true;
               }

               if (this.gO.xK().q() != 256 && var3.size() < 16) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void q(Set var1, long var2) {
      int var4 = this.q;
      if ((var2 & 1L) != 0L) {
         var4 = 16;
         var2--;
      }

      long var5 = var2;
      var1.add(var2);
      int var8 = 0;

      fA var7;
      do {
         var7 = OC.q(this.zz, this.lm, var5 + var8, var4);
         if (var7 == null) {
            break;
         }

         var8 += var7.getSize();
         if (var8 >= 30) {
            var5 += var8;
            var8 = 0;
            var1.add(var5);
         }
      } while (!var7.q().isPCUpdated() && var5 + var8 - var2 <= 128L);

      if (var8 > 0) {
         var1.add(var5 + var8);
      }
   }

   private boolean q(long var1) {
      if (this.q == 64) {
         if (oW) {
            Long var3 = VirtualMemoryUtil.readAsLongSafe(this.lm, var1 - 4L, 4);
            Long var4 = VirtualMemoryUtil.readAsLongSafe(this.lm, var1, 4);
            if (var3 != null && var3.intValue() == 0L || var4 != null && var4 == 0L) {
               return false;
            }
         }

         fA var5 = OC.q(this.nf, var1 - 4L);
         if (var5 != null && !OC.oW(var5) && !this.gP.gO(var1)) {
            return true;
         }
      }

      return false;
   }

   private boolean RF(long var1) {
      if (this.q == 64) {
         fA var3 = OC.q(this.nf, var1 - 4L);
         if (var3 != null && var3.Dw().q().equals("RET") && this.gP.gO(var1)) {
            return true;
         }
      }

      return false;
   }

   public static void q(boolean var0) {
      oW = var0;
   }

   private static enum eo {
      q,
      RF,
      xK,
      Dw;
   }
}
