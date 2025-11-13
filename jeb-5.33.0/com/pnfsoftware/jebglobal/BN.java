package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class BN {
   private static final ji pC = new Uf.Av(null, Uf.pC, 3, 3, -3);
   private static final tz A = new UC("VEXT", BS.NS, BS.vP, BS.xC, new IV(DirectEncodedMemoryArea.get(8, 3))).pC(pC);
   private static final tz kS = new UC("VEXT", BS.NS, BS.vP, BS.xC, new IV(DirectEncodedMemoryArea.get(8, 4))).pC(pC);
   private static final Hu wS = new cB(DirectEncodedMemoryArea.get(8, 2));
   private static final tz UT = new UC("VTBL", BS.z, wS, BS.hK).pC(pC);
   private static final tz E = new UC("VTBX", BS.z, wS, BS.hK).pC(pC);
   private static final Hu sY = new Yu(BS.hK, new FunctionEncodedMemoryArea(32, BN::pC));
   private static final ji ys = new gm(null, null, 0);
   private static final tz ld = new UC("VDUP", BS.NS, sY).pC(ys);

   protected static Long pC(byte[] var0) {
      IEncodedMemoryArea var1 = A(var0);
      return var1 != null ? var1.decode(var0) : null;
   }

   protected static IEncodedMemoryArea A(byte[] var0) {
      int var1 = kS(var0);
      if (var1 == 8) {
         return DirectEncodedMemoryArea.get(17, 3);
      } else if (var1 == 16) {
         return DirectEncodedMemoryArea.get(18, 2);
      } else {
         return var1 == 32 ? DirectEncodedMemoryArea.get(19, 1) : null;
      }
   }

   private static int kS(byte[] var0) {
      int var1 = var0[1] & 15;
      if ((var1 & 1) == 1) {
         return 8;
      } else if ((var1 & 3) == 2) {
         return 16;
      } else {
         return (var1 & 7) == 4 ? 32 : -1;
      }
   }

   public static tz pC(byte[] var0, int var1) {
      int var2 = (var0[1] & 128) >>> 7;
      int var3 = (var0[3] & 16) >>> 4;
      if (var2 == 0) {
         return nz.pC(var0, var1);
      } else if (var3 != 0) {
         return (var0[1] & 56) == 0 && (var0[3] & 128) == 0 ? Lh.pC(var0) : CE.pC(var0, var1);
      } else {
         int var4 = (var0[1] & 48) >>> 4;
         int var5 = (var0[3] & 64) >>> 6;
         if (var4 == 3) {
            int var6 = var0[2] & 15;
            int var7 = (var0[3] & 240) >>> 4;
            int var8 = Uf.pC(var0, var1);
            if (var8 == 0) {
               return (var7 & 4) == 0 ? A : kS;
            } else if ((var6 & 8) == 0) {
               return su.pC(var0, var1);
            } else if ((var6 & 12) == 8) {
               return (var7 & 4) == 0 ? UT : E;
            } else {
               return var6 == 12 ? ld : null;
            }
         } else {
            return var5 == 0 ? Zu.pC(var0, var1) : aZ.pC(var0, var1);
         }
      }
   }
}
