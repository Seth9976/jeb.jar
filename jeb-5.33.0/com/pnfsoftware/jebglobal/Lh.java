package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class Lh {
   private static final Hu pC = new Lh.Av();
   private static final tz[] A = new tz[]{
      new UC(0, "VMOV", Uf.UO, BS.NS, pC),
      new UC(1, "VORR", Uf.UO, BS.NS, pC),
      new UC(2, "VMOV", Uf.UO, BS.NS, pC),
      new UC(3, "VORR", Uf.UO, BS.NS, pC),
      new UC(4, "VMOV", Uf.UO, BS.NS, pC),
      new UC(5, "VORR", Uf.UO, BS.NS, pC),
      new UC(6, "VMOV", Uf.UO, BS.NS, pC),
      new UC(7, "VORR", Uf.UO, BS.NS, pC),
      new UC(8, "VMOV", Uf.Ab, BS.NS, pC),
      new UC(9, "VORR", Uf.Ab, BS.NS, pC),
      new UC(10, "VMOV", Uf.Ab, BS.NS, pC),
      new UC(11, "VORR", Uf.Ab, BS.NS, pC),
      new UC(12, "VMOV", Uf.UO, BS.NS, pC),
      new UC(13, "VMOV", Uf.UO, BS.NS, pC),
      new UC(14, "VMOV", Uf.rl, BS.NS, pC),
      new UC(15, "VMOV", Uf.ah, BS.NS, pC),
      new UC(0, "VMVN", Uf.UO, BS.NS, pC),
      new UC(1, "VBIC", Uf.UO, BS.NS, pC),
      new UC(2, "VMVN", Uf.UO, BS.NS, pC),
      new UC(3, "VBIC", Uf.UO, BS.NS, pC),
      new UC(4, "VMVN", Uf.UO, BS.NS, pC),
      new UC(5, "VBIC", Uf.UO, BS.NS, pC),
      new UC(6, "VMVN", Uf.UO, BS.NS, pC),
      new UC(7, "VBIC", Uf.UO, BS.NS, pC),
      new UC(8, "VMVN", Uf.Ab, BS.NS, pC),
      new UC(9, "VBIC", Uf.Ab, BS.NS, pC),
      new UC(10, "VMVN", Uf.Ab, BS.NS, pC),
      new UC(11, "VBIC", Uf.Ab, BS.NS, pC),
      new UC(12, "VMVN", Uf.UO, BS.NS, pC),
      new UC(13, "VMVN", Uf.UO, BS.NS, pC),
      new UC(14, "VMOV", Uf.eP, BS.NS, pC),
      UC.pC
   };

   public static tz pC(byte[] var0) {
      int var1 = var0[3] & 32;
      int var2 = var0[2] & 15;
      return A[var1 >>> 1 | var2];
   }

   private static class Av implements Hu {
      private static final IEncodedMemoryArea pC = new EncodedMemoryAreaList(
         DirectEncodedMemoryArea.get(24, 1), DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(0, 4)
      );
      private static final IEncodedMemoryArea A = new EncodedMemoryAreaList(
         DirectEncodedMemoryArea.get(28, 1), DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(0, 4)
      );

      private static IEncodedMemoryArea pC(int var0) {
         return var0 == 16 ? A : pC;
      }

      @Override
      public Yg buildOperand(byte[] var1, int var2) {
         int var3 = DirectEncodedMemoryArea.get(5, 1).decodeInt(var1);
         int var4 = var1[2] & 15;
         if (var3 == 0 && var4 == 15) {
            return new eK(32, pC(var2)).buildOperand(var1, var2);
         } else {
            int var5 = Gq.A(var1, pC(var2));
            return Yg.pC(64, Gq.pC(var3, var4, (long)var5));
         }
      }
   }
}
