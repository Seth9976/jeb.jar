package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class Ua {
   public static tz pC(byte[] var0, int var1) throws ProcessorException {
      int var2 = var0[0] & 3;
      int var3 = (var0[2] & 12) >> 2;
      int var4 = (var0[3] & 16) >> 4;
      if ((var0[0] & 240) == 240) {
         if (var3 < 2 || (var0[2] & 14) == 14) {
            if ((var0[0] & 3) == 0 && (var0[1] & 224) == 64) {
               return fv.UT[fv.pC(var0)];
            }

            if ((var0[0] & 3) == 2) {
               if ((var0[3] & 16) != 0) {
                  return fv.wS[fv.pC(var0)];
               }

               if ((var0[2] & 14) != 10) {
                  return fv.ys;
               }
            } else if ((var0[0] & 2) == 0) {
               return fv.E[fv.A(var0)];
            }
         }

         return var3 >= 2 ? mz.pC(var0, 32) : null;
      } else {
         if (var3 < 2) {
            tz var5 = pC(var0);
            if (var5 != null) {
               return var5;
            }
         }

         if (var3 < 2) {
            pC(var0, "Advanced SIMD");
         }

         if (var2 != 0 && var2 != 1) {
            if (var2 == 2) {
               if (var4 == 0) {
                  if (var3 == 3) {
                     pC(var0, "Advanced SIMD");
                  }

                  return mY.pC(var0, var1);
               }

               int var10 = var0[2] & 7;
               if (var10 == 6 || var10 == 7) {
                  return fv.wS[ZC.wS(var0, 0)];
               }

               if (var10 < 4) {
                  return kE.pC(var0, var1);
               }
            }

            return null;
         } else {
            int var9 = (var0[2] & 6) >>> 1;
            if (var9 == 2) {
               pC(var0, "Advanced SIMD");
            }

            int var6 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
            if (var9 == 3) {
               int var7 = (var6 & 2) >>> 1;
               if ((var6 & 13) == 0) {
                  String var11 = "System register 64-bit move";
                  if (var7 == 0) {
                     pC(var0, var11);
                  }

                  return fv.UT[ZC.wS(var0, 0)];
               } else {
                  String var8 = "System register load/store";
                  if (var7 != 0) {
                     pC(var0, var8);
                  }

                  return fv.E[fv.kS(var0)];
               }
            } else {
               return Sz.pC(var0, var1);
            }
         }
      }
   }

   public static void pC(byte[] var0, String var1) throws ProcessorException {
      UC.pC(var0, var1);
   }

   private static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 14) >>> 1;
      if (var1 == 6) {
         int var2 = var0[2] & 15;
         if ((var2 & 14) == 10) {
            return null;
         }

         int var3 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
         if ((var3 & 13) == 0) {
            if (var3 == 0) {
               pC(var0, "Coprocessor 64-bit Move");
            }

            return fv.UT[fv.pC(var0)];
         }

         if (ZC.UT(var0, 0) == 0) {
            return fv.E[fv.A(var0)];
         }
      } else if (var1 == 7) {
         int var4 = var0[2] & 15;
         if ((var0[0] & 1) == 0) {
            if ((var4 & 14) == 10) {
               return null;
            }

            int var6 = (var0[3] & 16) >>> 4;
            if (var6 == 0 && (var0[0] & 240) != 240) {
               return fv.sY;
            }

            return fv.wS[fv.pC(var0)];
         }

         int var5 = (var0[3] & 16) >>> 4;
         if (var5 == 0) {
            return null;
         }

         if (ZC.UT(var0, 0) == 0) {
            return fv.wS[fv.pC(var0)];
         }
      }

      return null;
   }
}
