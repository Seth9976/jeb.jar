package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class My {
   private static final Ef xK = new VP(Y.CE, new FunctionEncodedMemoryArea(32, My::q));
   private static final de Dw = new vr(null, null, 0);
   private static final de Uv = new pp();
   private static final de oW = new ld(null, null, 0);
   public static final Je[] q = new Je[]{new Qg("VMOV", Y.EB, Y.Xo, Pc.nf, Pc.lm).q(QI.gO), new Qg("VMOV", Pc.nf, Pc.lm, Y.EB, Y.Xo).q(QI.Dw)};
   public static final Je[] RF = new Je[]{new Qg("VMOV", Y.wF, Pc.nf, Pc.lm).q(QI.oW), new Qg("VMOV", Pc.nf, Pc.lm, Y.wF).q(QI.Dw)};
   private static final Je[] gO = new Je[]{
      new Qg("VMOV", Y.Rr, Pc.nf).q(Zo.Hk).q(MX.za),
      new Qg("VMOV", Pc.nf, Y.Rr).RF().q(Zo.Hk).q(MX.za),
      new Qg("VMOV", Y.Rr, Pc.nf),
      new Qg("VMOV", Pc.nf, Y.Rr).RF()
   };
   private static final Je[] nf = new Je[]{new Qg("VMSR", Y.GY, Pc.nf), new Qg("VMRS", Pc.JY, Y.GY)};
   private static final Je[] gP = new Je[]{new Qg("VMOV", xK, Pc.nf).q(Dw), new Qg("VMOV", Pc.nf, xK).RF().q(Uv)};
   private static final Je za = new Qg("VDUP", new Y(Y.lm, DirectEncodedMemoryArea.get(21, 1)), Pc.nf).q(oW);

   protected static Long q(byte[] var0) {
      IEncodedMemoryArea var1 = RF(var0);
      return var1 != null ? var1.decode(var0) : null;
   }

   protected static IEncodedMemoryArea RF(byte[] var0) {
      int var1 = xK(var0);
      int var2 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
      if (var1 == 8) {
         return VirtualEncodedMemoryArea.get(var2 & 7, 3);
      } else if (var1 == 16) {
         return VirtualEncodedMemoryArea.get((var2 & 6) >>> 1, 2);
      } else {
         return var1 == 32 ? VirtualEncodedMemoryArea.get((var2 & 4) >>> 2, 1) : null;
      }
   }

   private static int xK(byte[] var0) {
      int var1 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
      if ((var1 & 8) == 8) {
         return 8;
      } else if ((var1 & 1) == 1) {
         return 16;
      } else {
         return (var1 & 2) == 0 ? 32 : -1;
      }
   }

   private static int Dw(byte[] var0) {
      int var1 = (var0[1] & 64) >>> 5 | (var0[3] & 32) >>> 5;
      switch (var1) {
         case 0:
            return 32;
         case 1:
            return 16;
         case 2:
            return 8;
         default:
            return -1;
      }
   }

   public static Je q(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[1] & 16) >>> 4;
      int var3 = var0[2] & 3;
      int var4 = var0[1] & 224;
      if ((var3 == 1 || var3 == 2) && var4 == 0) {
         if (MX.q(var0) && !MX.RF(var0)) {
            return null;
         } else {
            return (var0[3] & 111) != 0 ? null : gO[var3 & 2 | var2];
         }
      } else {
         if (var3 == 2) {
            if (var4 == 224) {
               if ((var0[3] & 239) != 0) {
                  return null;
               }

               return nf[var2];
            }
         } else if (var3 == 3) {
            if (var2 == 1 || var2 == 0 && var4 < 128) {
               if ((var0[3] & 15) != 0) {
                  return null;
               }

               return gP[var2];
            }

            int var5 = var0[3] & 64;
            if (var5 == 0 && (var0[3] & 15) == 0) {
               return za;
            }
         }

         vf.q(var0, "SIMD");
         return null;
      }
   }
}
