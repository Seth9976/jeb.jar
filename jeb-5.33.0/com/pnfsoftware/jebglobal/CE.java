package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class CE {
   private static final Hu kS = new Zs(var0 -> {
      int var1 = pC(var0, DirectEncodedMemoryArea.get(19, 3), 1);
      if (var1 != 64) {
         var1 *= 2;
      }

      int var2 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return var1 - var2;
   });
   private static final Hu wS = new Zs(var0 -> {
      int var1 = pC(var0, DirectEncodedMemoryArea.get(19, 3), 1);
      if (var1 == 64) {
         var1 = 0;
      }

      int var2 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return var2 - var1;
   });
   private static final IEncodedMemoryArea UT = DirectEncodedMemoryArea.get(19, 3);
   private static final ji E = new CE.Av(null, UT);
   private static final ji sY = new CE.Av('S', UT);
   private static final ji ys = new CE.Av('U', UT);
   private static final ji ld = new CE.Av('I', UT);
   private static final ji gp = new CE.Av('I', UT, 2);
   private static final ji oT = new CE.Av('U', UT, 2);
   private static final ji fI = new CE.Av('S', UT, 2);
   private static final Hu WR = new OC(DirectEncodedMemoryArea.get(16, 6), 64);
   private static final tz[] NS = new tz[]{
      new UC(0, "VSHR", sY, BS.NS, BS.xC, kS),
      new UC(1, "VSHR", ys, BS.NS, BS.xC, kS),
      new UC(2, "VSRA", sY, BS.NS, BS.xC, kS),
      new UC(3, "VSRA", ys, BS.NS, BS.xC, kS),
      new UC(4, "VRSHR", sY, BS.NS, BS.xC, kS),
      new UC(5, "VRSHR", ys, BS.NS, BS.xC, kS),
      new UC(6, "VRSRA", sY, BS.NS, BS.xC, kS),
      new UC(7, "VRSRA", ys, BS.NS, BS.xC, kS),
      null,
      new UC(9, "VSRI", E, BS.NS, BS.xC, kS),
      new UC(10, "VSHL", ld, BS.NS, BS.xC, wS),
      new UC(11, "VSLI", E, BS.NS, BS.xC, wS),
      null,
      new UC(13, "VQSHLU", ys, BS.NS, BS.xC, wS),
      new UC(14, "VQSHL", sY, BS.NS, BS.xC, wS),
      new UC(15, "VQSHL", ys, BS.NS, BS.xC, wS)
   };
   private static final tz[] vP = new tz[]{
      new UC(0, "VSHRN", gp, BS.z, BS.UO, kS),
      new UC(1, "VRSHRN", gp, BS.z, BS.UO, kS),
      new UC(2, "VQSHRUN", fI, BS.z, BS.UO, kS),
      new UC(3, "VQRSHRUN", fI, BS.z, BS.UO, kS)
   };
   private static final tz[] xC = new tz[]{
      new UC(0, "VQSHRN", fI, BS.z, BS.UO, kS),
      new UC(1, "VQRSHRN", fI, BS.z, BS.UO, kS),
      new UC(0, "VQSHRN", oT, BS.z, BS.UO, kS),
      new UC(1, "VQRSHRN", oT, BS.z, BS.UO, kS)
   };
   private static final tz ED = new UC("VSHLL", BS.ah, BS.hK, wS).pC(sY);
   private static final tz Sc = new UC("VSHLL", BS.ah, BS.hK, wS).pC(ys);
   private static final tz ah = new UC("VMOVL", BS.ah, BS.hK).pC(sY);
   private static final tz eP = new UC("VMOVL", BS.ah, BS.hK).pC(ys);
   public static final ZW pC = new ZW(DirectEncodedMemoryArea.get(20, 1), 1L, 256L);
   public static final ZW A = new ZW(DirectEncodedMemoryArea.get(21, 1), 1L, 0L);
   private static final tz[] UO = new tz[]{
      new UC(0, "VCVT", Uf.Cu, BS.NS, BS.xC, WR).pC(A).pC(pC),
      new UC(1, "VCVT", Uf.sO, BS.NS, BS.xC, WR).pC(A).pC(pC),
      new UC(16, "VCVT", Uf.Pe, BS.NS, BS.xC, WR).pC(A).pC(pC),
      new UC(17, "VCVT", Uf.ck, BS.NS, BS.xC, WR).pC(A).pC(pC),
      new UC(256, "VCVT", Uf.PR, BS.NS, BS.xC, WR).pC(A),
      new UC(257, "VCVT", Uf.DQ, BS.NS, BS.xC, WR).pC(A),
      new UC(272, "VCVT", Uf.OB, BS.NS, BS.xC, WR).pC(A),
      new UC(273, "VCVT", Uf.Bc, BS.NS, BS.xC, WR).pC(A)
   };

   private static int pC(byte[] var0, IEncodedMemoryArea var1, int var2) {
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

   public static tz pC(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Uf.pC(var0, var1);
      int var4 = (var0[3] & 64) >>> 6;
      int var5 = (var0[3] & 128) >>> 7;
      if (var2 < 8) {
         return NS[var2 << 1 | var3];
      } else {
         if (var5 == 0) {
            int var6 = (var0[1] & 56) >>> 3;
            if (var2 == 8) {
               return vP[var3 << 1 | var4];
            }

            if (var2 == 9) {
               return xC[var3 << 1 | var4];
            }

            if (var2 == 10 && var4 == 0) {
               int var8 = var0[1] & 7;
               if (var8 != 0 || var6 != 1 && var6 != 2 && var6 != 4) {
                  if (var3 == 0) {
                     return ED;
                  }

                  return Sc;
               }

               if (var3 == 0) {
                  return ah;
               }

               return eP;
            }

            if (var2 >= 12) {
               int var7 = var2 & 3;
               return UO[var7 << 1 | var3];
            }
         }

         return null;
      }
   }

   private static class Av extends Uf.Av {
      public Av(Character var1, IEncodedMemoryArea var2) {
         super(var1, var2, 0, 3, 1);
      }

      public Av(Character var1, IEncodedMemoryArea var2, int var3) {
         super(var1, var2, 0, 3, var3);
      }

      @Override
      protected int pC(byte[] var1) {
         return CE.pC(var1, this.FE, this.Er);
      }
   }
}
