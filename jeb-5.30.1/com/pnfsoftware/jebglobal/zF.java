package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class zF {
   private static final Dm q = Dm.q(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(10, 2), DirectEncodedMemoryArea.get(30, 1)));
   private static final Ef RF = hc.q(0, new AO(), q);
   private static final Ef xK = hc.q(0, new en(), q);
   private static final Ef Dw = new ie();
   private static final Ef Uv = Bf.q(YH.qa);
   private static final Ef oW = Bf.RF(YH.qa, YH.zz);
   private static final Je[] gO = new Je[]{
      new Qg("ST4", RF, Uv),
      new Qg("ST1", RF, Uv),
      new Qg("ST3", RF, Uv),
      new Qg("ST1", RF, Uv),
      new Qg("ST2", RF, Uv),
      new Qg("ST1", RF, Uv),
      new Qg("ST1", RF, Uv),
      null,
      new Qg("LD4", RF, Uv),
      new Qg("LD1", RF, Uv),
      new Qg("LD3", RF, Uv),
      new Qg("LD1", RF, Uv),
      new Qg("LD2", RF, Uv),
      new Qg("LD1", RF, Uv),
      new Qg("LD1", RF, Uv),
      null
   };
   private static final IEncodedMemoryArea nf = DirectEncodedMemoryArea.get(30, 1);
   private static Ef gP = new Mf(var0 -> 8 * (xK(var0) + 1) * (nf.decodeInt(var0) + 1));
   private static final Ef za = new zF.eo(gP);
   private static final Je[] lm = new Je[]{
      new Qg("ST4", RF, oW),
      new Qg("ST1", RF, oW),
      new Qg("ST3", RF, oW),
      new Qg("ST1", RF, oW),
      new Qg("ST2", RF, oW),
      new Qg("ST1", RF, oW),
      new Qg("ST1", RF, oW),
      null,
      new Qg("ST4", RF, za),
      new Qg("ST1", RF, za),
      new Qg("ST3", RF, za),
      new Qg("ST1", RF, za),
      new Qg("ST2", RF, za),
      new Qg("ST1", RF, za),
      new Qg("ST1", RF, za),
      null,
      new Qg("LD4", RF, oW),
      new Qg("LD1", RF, oW),
      new Qg("LD3", RF, oW),
      new Qg("LD1", RF, oW),
      new Qg("LD2", RF, oW),
      new Qg("LD1", RF, oW),
      new Qg("LD1", RF, oW),
      null,
      new Qg("LD4", RF, za),
      new Qg("LD1", RF, za),
      new Qg("LD3", RF, za),
      new Qg("LD1", RF, za),
      new Qg("LD2", RF, za),
      new Qg("LD1", RF, za),
      new Qg("LD1", RF, za),
      null
   };
   private static final Je[] zz = new Je[]{new Qg("STL1", Dw, Uv).q(QJ.mJ), new Qg("LDAP1", Dw, Uv).q(QJ.mJ)};
   private static final Je[] JY = new Je[]{
      new Qg("ST1", Dw, Uv),
      new Qg("ST3", Dw, Uv),
      new Qg("ST1", Dw, Uv),
      new Qg("ST3", Dw, Uv),
      new Qg("ST1", Dw, Uv),
      new Qg("ST1", Dw, Uv),
      new Qg("ST3", Dw, Uv),
      new Qg("ST3", Dw, Uv),
      new Qg("ST2", Dw, Uv),
      new Qg("ST4", Dw, Uv),
      new Qg("ST2", Dw, Uv),
      new Qg("ST4", Dw, Uv),
      new Qg("ST2", Dw, Uv),
      new Qg("ST2", Dw, Uv),
      new Qg("ST4", Dw, Uv),
      new Qg("ST4", Dw, Uv),
      new Qg("LD1", Dw, Uv),
      new Qg("LD3", Dw, Uv),
      new Qg("LD1", Dw, Uv),
      new Qg("LD3", Dw, Uv),
      new Qg("LD1", Dw, Uv),
      new Qg("LD1", Dw, Uv),
      new Qg("LD3", Dw, Uv),
      new Qg("LD3", Dw, Uv),
      new Qg("LD2", Dw, Uv),
      new Qg("LD4", Dw, Uv),
      new Qg("LD2", Dw, Uv),
      new Qg("LD4", Dw, Uv),
      new Qg("LD2", Dw, Uv),
      new Qg("LD2", Dw, Uv),
      new Qg("LD4", Dw, Uv),
      new Qg("LD4", Dw, Uv)
   };
   private static final Je[] HF = new Je[]{new Qg("LD1R", xK, Uv), new Qg("LD3R", xK, Uv), new Qg("LD2R", xK, Uv), new Qg("LD4R", xK, Uv)};
   private static Ef LK = new Mf(var0 -> Uv(var0) * (Dw(var0) + 1));
   private static final Ef io = new zF.eo(LK);
   private static final Je[] qa = new Je[]{
      new Qg("ST1", Dw, oW),
      new Qg("ST3", Dw, oW),
      new Qg("ST1", Dw, oW),
      new Qg("ST3", Dw, oW),
      new Qg("ST1", Dw, oW),
      new Qg("ST1", Dw, oW),
      new Qg("ST3", Dw, oW),
      new Qg("ST3", Dw, oW),
      new Qg("ST1", Dw, io),
      new Qg("ST3", Dw, io),
      new Qg("ST1", Dw, io),
      new Qg("ST3", Dw, io),
      new Qg("ST1", Dw, io),
      new Qg("ST1", Dw, io),
      new Qg("ST3", Dw, io),
      new Qg("ST3", Dw, io),
      new Qg("ST2", Dw, oW),
      new Qg("ST4", Dw, oW),
      new Qg("ST2", Dw, oW),
      new Qg("ST4", Dw, oW),
      new Qg("ST2", Dw, oW),
      new Qg("ST2", Dw, oW),
      new Qg("ST4", Dw, oW),
      new Qg("ST4", Dw, oW),
      new Qg("ST2", Dw, io),
      new Qg("ST4", Dw, io),
      new Qg("ST2", Dw, io),
      new Qg("ST4", Dw, io),
      new Qg("ST2", Dw, io),
      new Qg("ST2", Dw, io),
      new Qg("ST4", Dw, io),
      new Qg("ST4", Dw, io),
      new Qg("LD1", Dw, oW),
      new Qg("LD3", Dw, oW),
      new Qg("LD1", Dw, oW),
      new Qg("LD3", Dw, oW),
      new Qg("LD1", Dw, oW),
      new Qg("LD1", Dw, oW),
      new Qg("LD3", Dw, oW),
      new Qg("LD3", Dw, oW),
      new Qg("LD1", Dw, io),
      new Qg("LD3", Dw, io),
      new Qg("LD1", Dw, io),
      new Qg("LD3", Dw, io),
      new Qg("LD1", Dw, io),
      new Qg("LD1", Dw, io),
      new Qg("LD3", Dw, io),
      new Qg("LD3", Dw, io),
      new Qg("LD2", Dw, oW),
      new Qg("LD4", Dw, oW),
      new Qg("LD2", Dw, oW),
      new Qg("LD4", Dw, oW),
      new Qg("LD2", Dw, oW),
      new Qg("LD2", Dw, oW),
      new Qg("LD4", Dw, oW),
      new Qg("LD4", Dw, oW),
      new Qg("LD2", Dw, io),
      new Qg("LD4", Dw, io),
      new Qg("LD2", Dw, io),
      new Qg("LD4", Dw, io),
      new Qg("LD2", Dw, io),
      new Qg("LD2", Dw, io),
      new Qg("LD4", Dw, io),
      new Qg("LD4", Dw, io)
   };
   private static Ef Hk = new Mf(var0 -> {
      int var1 = Dw(var0) + 1;
      int var2 = (var0[2] & 12) >>> 2;
      return var1 * (int)Math.pow(2.0, var2);
   });
   private static final Ef Me = new zF.eo(Hk);
   private static final Je[] PV = new Je[]{
      new Qg("LD1R", xK, oW),
      new Qg("LD3R", xK, oW),
      new Qg("LD1R", xK, Me),
      new Qg("LD3R", xK, Me),
      new Qg("LD2R", xK, oW),
      new Qg("LD4R", xK, oW),
      new Qg("LD2R", xK, Me),
      new Qg("LD4R", xK, Me)
   };

   private static int RF(byte[] var0) {
      int var1 = (var0[2] & 240) >>> 4;
      return (var1 & 1) == 1 ? 6 : var1 >>> 1;
   }

   private static int xK(byte[] var0) {
      return ~(RF(var0) >>> 1) & 3;
   }

   private static int Dw(byte[] var0) {
      int var1 = (var0[2] & 255) >>> 5;
      int var2 = (var0[1] & 32) >>> 5;
      return ((var1 & 1) == 0 ? 0 : 2) + var2;
   }

   private static Integer q(int var0, int var1) {
      int var2 = var0 >>> 1;
      switch (var2) {
         case 0:
         case 1:
            return var2;
         case 2:
         case 3:
            if ((var1 & 1) == 0) {
               return var2;
            }

            return null;
         case 4:
         case 5:
            if (var2 == 5) {
               var2 = 6;
            }

            if (var1 == 0) {
               return var2;
            } else {
               if ((var0 & 1) == 0 && var1 == 1) {
                  return var2 + 1;
               }

               return null;
            }
         default:
            return null;
      }
   }

   private static int Uv(byte[] var0) {
      int var1 = (var0[2] & 255) >>> 6;
      switch (var1) {
         case 0:
            return 1;
         case 1:
            return 2;
         case 2:
            int var2 = (var0[2] & 12) >>> 2;
            return var2 == 0 ? 4 : 8;
         case 3:
         default:
            return -1;
      }
   }

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 128) >>> 7;
      int var2 = DirectEncodedMemoryArea.get(23, 2).decodeInt(var0);
      int var3 = var0[1] & 63;
      int var4 = RF(var0);
      Integer var5 = q((var0[2] & 240) >>> 4, (var0[2] & 12) >>> 2);
      int var6 = (var0[1] & 64) >>> 6;
      int var7 = (var0[1] & 32) >>> 5;
      int var8 = var0[1] & 31;
      if (var1 == 0) {
         switch (var2) {
            case 0:
               if (var3 == 0) {
                  return gO[var6 << 3 | var4];
               }
               break;
            case 1:
               if (var3 < 32) {
                  return lm[var6 << 4 | (var8 == 31 ? 1 : 0) << 3 | var4];
               }
               break;
            case 2:
               if ((var3 & 31) == 0) {
                  if (var5 != null) {
                     return JY[var6 << 4 | var7 << 3 | var5];
                  }

                  if (var6 == 1 && (var0[2] & 208) == 192) {
                     return HF[var7 << 1 | (var0[2] & 32) >>> 5];
                  }
               } else if (var3 == 1 && (var0[2] & 252) == 132) {
                  return zz[var6];
               }
               break;
            case 3:
               if (var5 != null) {
                  return qa[var6 << 5 | var7 << 4 | (var8 == 31 ? 1 : 0) << 3 | var5];
               }

               if (var6 == 1 && (var0[2] & 208) == 192) {
                  return PV[var7 << 2 | (var8 == 31 ? 1 : 0) << 1 | (var0[2] & 32) >>> 5];
               }
         }
      }

      return Qg.q(var0, "LoadStore: SIMD");
   }

   private static class eo implements Ef {
      private Ef q;

      public eo(Ef var1) {
         this.q = var1;
      }

      @Override
      public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
         return new wh(YH.Me.buildOperand(var1, var2), this.q.buildOperand(var1, var2), false, false, var2);
      }
   }
}
