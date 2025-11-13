package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class Ei {
   public static Je q(byte[] var0, int var1) throws ProcessorException {
      int var2 = var0[0] & 3;
      int var3 = (var0[2] & 12) >> 2;
      int var4 = (var0[3] & 16) >> 4;
      if ((var0[0] & 240) == 240) {
         if (var3 < 2 || (var0[2] & 14) == 14) {
            if ((var0[0] & 3) == 0 && (var0[1] & 224) == 64) {
               return ZH.Uv[ZH.q(var0)];
            }

            if ((var0[0] & 3) == 2) {
               if ((var0[3] & 16) != 0) {
                  return ZH.Dw[ZH.q(var0)];
               }

               if ((var0[2] & 14) != 10) {
                  return ZH.nf;
               }
            } else if ((var0[0] & 2) == 0) {
               return ZH.oW[ZH.RF(var0)];
            }
         }

         return var3 >= 2 ? Lw.q(var0, 32) : null;
      } else {
         if (var3 < 2) {
            Je var5 = q(var0);
            if (var5 != null) {
               return var5;
            }
         }

         if (var3 < 2) {
            q(var0, "Advanced SIMD");
         }

         if (var2 != 0 && var2 != 1) {
            if (var2 == 2) {
               if (var4 == 0) {
                  if (var3 == 3) {
                     q(var0, "Advanced SIMD");
                  }

                  return zN.q(var0, var1);
               }

               int var10 = var0[2] & 7;
               if (var10 == 6 || var10 == 7) {
                  return ZH.Dw[HS.Dw(var0, 0)];
               }

               if (var10 < 4) {
                  return My.q(var0, var1);
               }
            }

            return null;
         } else {
            int var9 = (var0[2] & 6) >>> 1;
            if (var9 == 2) {
               q(var0, "Advanced SIMD");
            }

            int var6 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
            if (var9 == 3) {
               int var7 = (var6 & 2) >>> 1;
               if ((var6 & 13) == 0) {
                  String var11 = "System register 64-bit move";
                  if (var7 == 0) {
                     q(var0, var11);
                  }

                  return ZH.Uv[HS.Dw(var0, 0)];
               } else {
                  String var8 = "System register load/store";
                  if (var7 != 0) {
                     q(var0, var8);
                  }

                  return ZH.oW[ZH.xK(var0)];
               }
            } else {
               return mD.q(var0, var1);
            }
         }
      }
   }

   public static void q(byte[] var0, String var1) throws ProcessorException {
      Qg.q(var0, var1);
   }

   private static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 14) >>> 1;
      if (var1 == 6) {
         int var2 = var0[2] & 15;
         if ((var2 & 14) == 10) {
            return null;
         }

         int var3 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
         if ((var3 & 13) == 0) {
            if (var3 == 0) {
               q(var0, "Coprocessor 64-bit Move");
            }

            return ZH.Uv[ZH.q(var0)];
         }

         if (HS.Uv(var0, 0) == 0) {
            return ZH.oW[ZH.RF(var0)];
         }
      } else if (var1 == 7) {
         int var4 = var0[2] & 15;
         if ((var0[0] & 1) == 0) {
            if ((var4 & 14) == 10) {
               return null;
            }

            int var6 = (var0[3] & 16) >>> 4;
            if (var6 == 0 && (var0[0] & 240) != 240) {
               return ZH.gO;
            }

            return ZH.Dw[ZH.q(var0)];
         }

         int var5 = (var0[3] & 16) >>> 4;
         if (var5 == 0) {
            return null;
         }

         if (HS.Uv(var0, 0) == 0) {
            return ZH.Dw[ZH.q(var0)];
         }
      }

      return null;
   }
}
