package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class kE {
   private static final Hu kS = new Yu(BS.Ek, new FunctionEncodedMemoryArea(32, kE::pC));
   private static final ji wS = new nb(null, null, 0);
   private static final ji UT = new Xq();
   private static final ji E = new wB(null, null, 0);
   public static final tz[] pC = new tz[]{new UC("VMOV", BS.os, BS.Cu, wT.UT, wT.ys).pC(cT.sY), new UC("VMOV", wT.UT, wT.ys, BS.os, BS.Cu).pC(cT.wS)};
   public static final tz[] A = new tz[]{new UC("VMOV", BS.hK, wT.UT, wT.ys).pC(cT.E), new UC("VMOV", wT.UT, wT.ys, BS.hK).pC(cT.wS)};
   private static final tz[] sY = new tz[]{
      new UC("VMOV", BS.sO, wT.UT).pC(Uf.Sc).pC(ze.pC),
      new UC("VMOV", wT.UT, BS.sO).A().pC(Uf.Sc).pC(ze.pC),
      new UC("VMOV", BS.sO, wT.UT),
      new UC("VMOV", wT.UT, BS.sO).A()
   };
   private static final tz[] ys = new tz[]{new UC("VMSR", BS.OI, wT.UT), new UC("VMRS", wT.gp, BS.OI)};
   private static final tz[] ld = new tz[]{new UC("VMOV", kS, wT.UT).pC(wS), new UC("VMOV", wT.UT, kS).A().pC(UT)};
   private static final tz gp = new UC("VDUP", new BS(BS.ys, DirectEncodedMemoryArea.get(21, 1)), wT.UT).pC(E);

   protected static Long pC(byte[] var0) {
      IEncodedMemoryArea var1 = A(var0);
      return var1 != null ? var1.decode(var0) : null;
   }

   protected static IEncodedMemoryArea A(byte[] var0) {
      int var1 = kS(var0);
      int var2 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
      if (var1 == 8) {
         return VirtualEncodedMemoryArea.get(var2 & 7, 3);
      } else if (var1 == 16) {
         return VirtualEncodedMemoryArea.get((var2 & 6) >>> 1, 2);
      } else {
         return var1 == 32 ? VirtualEncodedMemoryArea.get((var2 & 4) >>> 2, 1) : null;
      }
   }

   private static int kS(byte[] var0) {
      int var1 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
      if ((var1 & 8) == 8) {
         return 8;
      } else if ((var1 & 1) == 1) {
         return 16;
      } else {
         return (var1 & 2) == 0 ? 32 : -1;
      }
   }

   private static int wS(byte[] var0) {
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

   public static tz pC(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[1] & 16) >>> 4;
      int var3 = var0[2] & 3;
      int var4 = var0[1] & 224;
      if ((var3 == 1 || var3 == 2) && var4 == 0) {
         if (ze.pC(var0) && !ze.A(var0)) {
            return null;
         } else {
            return (var0[3] & 111) != 0 ? null : sY[var3 & 2 | var2];
         }
      } else {
         if (var3 == 2) {
            if (var4 == 224) {
               if ((var0[3] & 239) != 0) {
                  return null;
               }

               return ys[var2];
            }
         } else if (var3 == 3) {
            if (var2 == 1 || var2 == 0 && var4 < 128) {
               if ((var0[3] & 15) != 0) {
                  return null;
               }

               return ld[var2];
            }

            int var5 = var0[3] & 64;
            if (var5 == 0 && (var0[3] & 15) == 0) {
               return gp;
            }
         }

         Rb.pC(var0, "SIMD");
         return null;
      }
   }
}
