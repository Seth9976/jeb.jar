package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class Iu {
   private static final Ef xK = new Mf(var0 -> {
      int var1 = q(var0, DirectEncodedMemoryArea.get(19, 3), 1);
      if (var1 != 64) {
         var1 *= 2;
      }

      int var2 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return var1 - var2;
   });
   private static final Ef Dw = new Mf(var0 -> {
      int var1 = q(var0, DirectEncodedMemoryArea.get(19, 3), 1);
      if (var1 == 64) {
         var1 = 0;
      }

      int var2 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return var2 - var1;
   });
   private static final IEncodedMemoryArea Uv = DirectEncodedMemoryArea.get(19, 3);
   private static final de oW = new Iu.eo(null, Uv);
   private static final de gO = new Iu.eo('S', Uv);
   private static final de nf = new Iu.eo('U', Uv);
   private static final de gP = new Iu.eo('I', Uv);
   private static final de za = new Iu.eo('I', Uv, 2);
   private static final de lm = new Iu.eo('U', Uv, 2);
   private static final de zz = new Iu.eo('S', Uv, 2);
   private static final Ef JY = new jO(DirectEncodedMemoryArea.get(16, 6), 64);
   private static final Je[] HF = new Je[]{
      new Qg(0, "VSHR", gO, Y.qa, Y.Me, xK),
      new Qg(1, "VSHR", nf, Y.qa, Y.Me, xK),
      new Qg(2, "VSRA", gO, Y.qa, Y.Me, xK),
      new Qg(3, "VSRA", nf, Y.qa, Y.Me, xK),
      new Qg(4, "VRSHR", gO, Y.qa, Y.Me, xK),
      new Qg(5, "VRSHR", nf, Y.qa, Y.Me, xK),
      new Qg(6, "VRSRA", gO, Y.qa, Y.Me, xK),
      new Qg(7, "VRSRA", nf, Y.qa, Y.Me, xK),
      null,
      new Qg(9, "VSRI", oW, Y.qa, Y.Me, xK),
      new Qg(10, "VSHL", gP, Y.qa, Y.Me, Dw),
      new Qg(11, "VSLI", oW, Y.qa, Y.Me, Dw),
      null,
      new Qg(13, "VQSHLU", nf, Y.qa, Y.Me, Dw),
      new Qg(14, "VQSHL", gO, Y.qa, Y.Me, Dw),
      new Qg(15, "VQSHL", nf, Y.qa, Y.Me, Dw)
   };
   private static final Je[] LK = new Je[]{
      new Qg(0, "VSHRN", za, Y.sH, Y.Gf, xK),
      new Qg(1, "VRSHRN", za, Y.sH, Y.Gf, xK),
      new Qg(2, "VQSHRUN", zz, Y.sH, Y.Gf, xK),
      new Qg(3, "VQRSHRUN", zz, Y.sH, Y.Gf, xK)
   };
   private static final Je[] io = new Je[]{
      new Qg(0, "VQSHRN", zz, Y.sH, Y.Gf, xK),
      new Qg(1, "VQRSHRN", zz, Y.sH, Y.Gf, xK),
      new Qg(0, "VQSHRN", lm, Y.sH, Y.Gf, xK),
      new Qg(1, "VQRSHRN", lm, Y.sH, Y.Gf, xK)
   };
   private static final Je qa = new Qg("VSHLL", Y.xW, Y.wF, Dw).q(gO);
   private static final Je Hk = new Qg("VSHLL", Y.xW, Y.wF, Dw).q(nf);
   private static final Je Me = new Qg("VMOVL", Y.xW, Y.wF).q(gO);
   private static final Je PV = new Qg("VMOVL", Y.xW, Y.wF).q(nf);
   public static final dD q = new dD(DirectEncodedMemoryArea.get(20, 1), 1L, 256L);
   public static final dD RF = new dD(DirectEncodedMemoryArea.get(21, 1), 1L, 0L);
   private static final Je[] oQ = new Je[]{
      new Qg(0, "VCVT", Zo.TX, Y.qa, Y.Me, JY).q(RF).q(q),
      new Qg(1, "VCVT", Zo.jq, Y.qa, Y.Me, JY).q(RF).q(q),
      new Qg(16, "VCVT", Zo.Ri, Y.qa, Y.Me, JY).q(RF).q(q),
      new Qg(17, "VCVT", Zo.GY, Y.qa, Y.Me, JY).q(RF).q(q),
      new Qg(256, "VCVT", Zo.Xo, Y.qa, Y.Me, JY).q(RF),
      new Qg(257, "VCVT", Zo.IN, Y.qa, Y.Me, JY).q(RF),
      new Qg(272, "VCVT", Zo.eJ, Y.qa, Y.Me, JY).q(RF),
      new Qg(273, "VCVT", Zo.Rv, Y.qa, Y.Me, JY).q(RF)
   };

   private static int q(byte[] var0, IEncodedMemoryArea var1, int var2) {
      int var3 = DirectEncodedMemoryArea.get(7, 1).decodeInt(var0);
      if (var3 == 0) {
         int var4 = var1.decodeInt(var0);
         if (var4 == 0) {
            return 0;
         } else if (var4 == 1) {
            return 8 * var2;
         } else {
            return var4 < 4 ? 16 * var2 : 32 * var2;
         }
      } else {
         return 64;
      }
   }

   public static Je q(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Zo.q(var0, var1);
      int var4 = (var0[3] & 64) >>> 6;
      int var5 = (var0[3] & 128) >>> 7;
      if (var2 < 8) {
         return HF[var2 << 1 | var3];
      } else {
         if (var5 == 0) {
            int var6 = (var0[1] & 56) >>> 3;
            if (var2 == 8) {
               return LK[var3 << 1 | var4];
            }

            if (var2 == 9) {
               return io[var3 << 1 | var4];
            }

            if (var2 == 10 && var4 == 0) {
               int var8 = var0[1] & 7;
               if (var8 != 0 || var6 != 1 && var6 != 2 && var6 != 4) {
                  if (var3 == 0) {
                     return qa;
                  }

                  return Hk;
               }

               if (var3 == 0) {
                  return Me;
               }

               return PV;
            }

            if (var2 >= 12) {
               int var7 = var2 & 3;
               return oQ[var7 << 1 | var3];
            }
         }

         return null;
      }
   }

   private static class eo extends Zo.eo {
      public eo(Character var1, IEncodedMemoryArea var2) {
         super(var1, var2, 0, 3, 1);
      }

      public eo(Character var1, IEncodedMemoryArea var2, int var3) {
         super(var1, var2, 0, 3, var3);
      }

      @Override
      protected int q(byte[] var1) {
         return Iu.q(var1, this.CE, this.sH);
      }
   }
}
