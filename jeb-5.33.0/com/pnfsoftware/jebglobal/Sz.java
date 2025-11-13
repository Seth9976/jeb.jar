package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Sz {
   private static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(0, 8);
   private static final Hu A = new Sz.Av(BS.LM, 2097158);
   private static final Hu kS = new Sz.Av(BS.UT, 4194310);
   private static final tz[] wS = new tz[]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(2, "VSTMIA", wT.ys, A),
      new UC(10, "VSTMIA", wT.ys, kS),
      null,
      null,
      new UC(2, "VLDMIA", wT.ys, A),
      new UC(10, "VLDMIA", wT.ys, kS),
      null,
      null,
      new UC(3, "VSTMIA", wT.ld, A).A(),
      new UC(11, "VSTMIA", wT.ld, kS).A(),
      null,
      null,
      new UC(3, "VLDMIA", wT.ld, A).A(),
      new UC(11, "VLDMIA", wT.ld, kS).A(),
      null,
      new UC(4, "VSTR", Uf.xC, BS.mv, LF.sY),
      new UC(4, "VSTR", BS.mv, LF.UT),
      new UC(12, "VSTR", BS.z, LF.UT),
      null,
      new UC(4, "VLDR", Uf.xC, BS.mv, LF.ys),
      new UC(4, "VLDR", BS.mv, LF.E),
      new UC(12, "VLDR", BS.z, LF.E),
      null,
      null,
      new UC(5, "VSTMDB", wT.ld, A).A(),
      new UC(13, "VSTMDB", wT.ld, kS).A(),
      null,
      null,
      new UC(5, "VLDMDB", wT.ld, A).A(),
      new UC(13, "VLDMDB", wT.ld, kS).A(),
      null,
      new UC(6, "VSTR", Uf.xC, BS.mv, LF.sY),
      new UC(6, "VSTR", BS.mv, LF.UT),
      new UC(14, "VSTR", BS.z, LF.UT),
      null,
      new UC(6, "VLDR", Uf.xC, BS.mv, LF.ys),
      new UC(6, "VLDR", BS.mv, LF.E),
      new UC(14, "VLDR", BS.z, LF.E),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
   };
   private static final tz UT = new UC("VPUSH", A);
   private static final tz E = new UC("VPUSH", kS);
   private static final tz[] sY = new tz[]{new UC("FSTMIAX", wT.ys, kS), new UC("FSTMIAX", wT.ld, kS).A()};
   private static final tz ys = new UC("FSTMDBX", wT.ld, kS);
   private static final tz ld = new UC("VPOP", A);
   private static final tz gp = new UC("VPOP", kS);
   private static final tz[] oT = new tz[]{new UC("FLDMIAX", wT.ys, kS), new UC("FLDMIAX", wT.ld, kS).A()};
   private static final tz fI = new UC("FLDMDBX", wT.ld, kS).A();

   public static tz pC(byte[] var0, int var1) {
      if ((var0[0] & 240) == 240) {
         return fv.E[fv.A(var0)];
      } else {
         int var2 = DirectEncodedMemoryArea.get(21, 4).decodeInt(var0);
         int var3 = ZC.wS(var0, 0);
         int var4 = var0[2] & 3;
         int var5 = ZC.pC(var0, 2) | ZC.A(var0, 1) | ZC.kS(var0, 0);
         switch (var2) {
            case 0:
            case 2:
               if ((var0[3] & 208) == 16 && (var0[1] & 64) == 64) {
                  int var8 = var0[2] & 1;
                  int var7 = (var0[1] & 16) >>> 4;
                  if (var8 == 0) {
                     return kE.pC[var7];
                  }

                  return kE.A[var7];
               }

               return null;
            case 1:
            case 3:
            case 13:
            default:
               return null;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
               int var6 = var0[1] & 15;
               if (var3 == 0) {
                  if (var4 == 3) {
                     if (var6 == 13 && var5 == 5 && (var0[3] & 1) == 0) {
                        return E;
                     }

                     if ((var5 == 2 || var5 == 3) && (var0[3] & 1) == 1) {
                        return sY[var5 & 1];
                     }

                     if (var5 == 5 && (var0[3] & 1) == 1) {
                        return ys;
                     }
                  } else if (var6 == 13 && var5 == 5 && var4 == 2) {
                     return UT;
                  }
               } else if (var4 == 3) {
                  if (var6 == 13 && var5 == 3 && (var0[3] & 1) == 0) {
                     return gp;
                  }

                  if ((var5 == 2 || var5 == 3) && (var0[3] & 1) == 1) {
                     return oT[var5 & 1];
                  }

                  if (var5 == 5 && (var0[3] & 1) == 1) {
                     return fI;
                  }
               } else if (var6 == 13 && var5 == 3 && var4 == 2) {
                  return ld;
               }

               return (var5 == 4 || var5 == 6) && ze.pC(var0) && !ze.A(var0) ? null : wS[var5 << 3 | var3 << 2 | var4];
         }
      }
   }

   private static class Av implements Hu {
      private IEncodedMemoryArea pC;
      private int A;

      public Av(IEncodedMemoryArea var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
         int var3 = Gq.A(var1, this.pC);
         int var4 = Gq.A(var1, Sz.pC);
         if (this.A == 4194310) {
            var4 /= 2;
         }

         return new Uw(0, 0, this.pC(var3, var2, var4));
      }

      private Yg[] pC(int var1, int var2, int var3) {
         Yg[] var4 = new Yg[var3];

         for (int var5 = 0; var5 < var3; var5++) {
            int var6 = var1 + var5;
            var4[var5] = LC.kS(this.A, var6, var2);
         }

         return var4;
      }

      @Override
      public String kS(byte[] var1) {
         int var2 = Gq.A(var1, Sz.pC);
         if (var2 == 0) {
            return "reglist must contain at least one register";
         } else {
            if (this.A == 4194310) {
               var2 /= 2;
            }

            int var3 = Gq.A(var1, this.pC);
            if (var3 + var2 > 32) {
               return "Illegal register for reglist (out of limit)";
            } else {
               return this.A == 4194310 && var2 > 16 ? "Oversized register size for reglist" : Hu.super.kS(var1);
            }
         }
      }
   }
}
