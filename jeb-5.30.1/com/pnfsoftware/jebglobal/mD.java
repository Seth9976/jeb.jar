package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class mD {
   private static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(0, 8);
   private static final Ef RF = new mD.eo(Y.ui, 2097158);
   private static final Ef xK = new mD.eo(Y.nf, 4194310);
   private static final Je[] Dw = new Je[]{
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
      new Qg(2, "VSTMIA", Pc.lm, RF),
      new Qg(10, "VSTMIA", Pc.lm, xK),
      null,
      null,
      new Qg(2, "VLDMIA", Pc.lm, RF),
      new Qg(10, "VLDMIA", Pc.lm, xK),
      null,
      null,
      new Qg(3, "VSTMIA", Pc.zz, RF).RF(),
      new Qg(11, "VSTMIA", Pc.zz, xK).RF(),
      null,
      null,
      new Qg(3, "VLDMIA", Pc.zz, RF).RF(),
      new Qg(11, "VLDMIA", Pc.zz, xK).RF(),
      null,
      new Qg(4, "VSTR", Zo.io, Y.TX, Bf.nf),
      new Qg(4, "VSTR", Y.TX, Bf.oW),
      new Qg(12, "VSTR", Y.sH, Bf.oW),
      null,
      new Qg(4, "VLDR", Zo.io, Y.TX, Bf.gP),
      new Qg(4, "VLDR", Y.TX, Bf.gO),
      new Qg(12, "VLDR", Y.sH, Bf.gO),
      null,
      null,
      new Qg(5, "VSTMDB", Pc.zz, RF).RF(),
      new Qg(13, "VSTMDB", Pc.zz, xK).RF(),
      null,
      null,
      new Qg(5, "VLDMDB", Pc.zz, RF).RF(),
      new Qg(13, "VLDMDB", Pc.zz, xK).RF(),
      null,
      new Qg(6, "VSTR", Zo.io, Y.TX, Bf.nf),
      new Qg(6, "VSTR", Y.TX, Bf.oW),
      new Qg(14, "VSTR", Y.sH, Bf.oW),
      null,
      new Qg(6, "VLDR", Zo.io, Y.TX, Bf.gP),
      new Qg(6, "VLDR", Y.TX, Bf.gO),
      new Qg(14, "VLDR", Y.sH, Bf.gO),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
   };
   private static final Je Uv = new Qg("VPUSH", RF);
   private static final Je oW = new Qg("VPUSH", xK);
   private static final Je[] gO = new Je[]{new Qg("FSTMIAX", Pc.lm, xK), new Qg("FSTMIAX", Pc.zz, xK).RF()};
   private static final Je nf = new Qg("FSTMDBX", Pc.zz, xK);
   private static final Je gP = new Qg("VPOP", RF);
   private static final Je za = new Qg("VPOP", xK);
   private static final Je[] lm = new Je[]{new Qg("FLDMIAX", Pc.lm, xK), new Qg("FLDMIAX", Pc.zz, xK).RF()};
   private static final Je zz = new Qg("FLDMDBX", Pc.zz, xK).RF();

   public static Je q(byte[] var0, int var1) {
      if ((var0[0] & 240) == 240) {
         return ZH.oW[ZH.RF(var0)];
      } else {
         int var2 = DirectEncodedMemoryArea.get(21, 4).decodeInt(var0);
         int var3 = HS.Dw(var0, 0);
         int var4 = var0[2] & 3;
         int var5 = HS.q(var0, 2) | HS.RF(var0, 1) | HS.xK(var0, 0);
         switch (var2) {
            case 0:
            case 2:
               if ((var0[3] & 208) == 16 && (var0[1] & 64) == 64) {
                  int var8 = var0[2] & 1;
                  int var7 = (var0[1] & 16) >>> 4;
                  if (var8 == 0) {
                     return My.q[var7];
                  }

                  return My.RF[var7];
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
                        return oW;
                     }

                     if ((var5 == 2 || var5 == 3) && (var0[3] & 1) == 1) {
                        return gO[var5 & 1];
                     }

                     if (var5 == 5 && (var0[3] & 1) == 1) {
                        return nf;
                     }
                  } else if (var6 == 13 && var5 == 5 && var4 == 2) {
                     return Uv;
                  }
               } else if (var4 == 3) {
                  if (var6 == 13 && var5 == 3 && (var0[3] & 1) == 0) {
                     return za;
                  }

                  if ((var5 == 2 || var5 == 3) && (var0[3] & 1) == 1) {
                     return lm[var5 & 1];
                  }

                  if (var5 == 5 && (var0[3] & 1) == 1) {
                     return zz;
                  }
               } else if (var6 == 13 && var5 == 3 && var4 == 2) {
                  return gP;
               }

               return (var5 == 4 || var5 == 6) && MX.q(var0) && !MX.RF(var0) ? null : Dw[var5 << 3 | var3 << 2 | var4];
         }
      }
   }

   private static class eo implements Ef {
      private IEncodedMemoryArea q;
      private int RF;

      public eo(IEncodedMemoryArea var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
         int var3 = k.RF(var1, this.q);
         int var4 = k.RF(var1, mD.q);
         if (this.RF == 4194310) {
            var4 /= 2;
         }

         return new eL(0, 0, this.q(var3, var2, var4));
      }

      private CW[] q(int var1, int var2, int var3) {
         CW[] var4 = new CW[var3];

         for (int var5 = 0; var5 < var3; var5++) {
            int var6 = var1 + var5;
            var4[var5] = GC.xK(this.RF, var6, var2);
         }

         return var4;
      }

      @Override
      public String xK(byte[] var1) {
         int var2 = k.RF(var1, mD.q);
         if (var2 == 0) {
            return "reglist must contain at least one register";
         } else {
            if (this.RF == 4194310) {
               var2 /= 2;
            }

            int var3 = k.RF(var1, this.q);
            if (var3 + var2 > 32) {
               return "Illegal register for reglist (out of limit)";
            } else {
               return this.RF == 4194310 && var2 > 16 ? "Oversized register size for reglist" : Ef.super.xK(var1);
            }
         }
      }
   }
}
