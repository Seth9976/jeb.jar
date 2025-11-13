package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class Rc {
   private static final Ef q = new Rc.eo();
   private static final Je[] RF = new Je[]{
      new Qg(0, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(1, "VORR", Zo.oQ, Y.qa, q),
      new Qg(2, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(3, "VORR", Zo.oQ, Y.qa, q),
      new Qg(4, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(5, "VORR", Zo.oQ, Y.qa, q),
      new Qg(6, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(7, "VORR", Zo.oQ, Y.qa, q),
      new Qg(8, "VMOV", Zo.xW, Y.qa, q),
      new Qg(9, "VORR", Zo.xW, Y.qa, q),
      new Qg(10, "VMOV", Zo.xW, Y.qa, q),
      new Qg(11, "VORR", Zo.xW, Y.qa, q),
      new Qg(12, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(13, "VMOV", Zo.oQ, Y.qa, q),
      new Qg(14, "VMOV", Zo.KT, Y.qa, q),
      new Qg(15, "VMOV", Zo.Me, Y.qa, q),
      new Qg(0, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(1, "VBIC", Zo.oQ, Y.qa, q),
      new Qg(2, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(3, "VBIC", Zo.oQ, Y.qa, q),
      new Qg(4, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(5, "VBIC", Zo.oQ, Y.qa, q),
      new Qg(6, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(7, "VBIC", Zo.oQ, Y.qa, q),
      new Qg(8, "VMVN", Zo.xW, Y.qa, q),
      new Qg(9, "VBIC", Zo.xW, Y.qa, q),
      new Qg(10, "VMVN", Zo.xW, Y.qa, q),
      new Qg(11, "VBIC", Zo.xW, Y.qa, q),
      new Qg(12, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(13, "VMVN", Zo.oQ, Y.qa, q),
      new Qg(14, "VMOV", Zo.PV, Y.qa, q),
      Qg.xK
   };

   public static Je q(byte[] var0) {
      int var1 = var0[3] & 32;
      int var2 = var0[2] & 15;
      return RF[var1 >>> 1 | var2];
   }

   private static class eo implements Ef {
      private static final IEncodedMemoryArea q = new EncodedMemoryAreaList(
         DirectEncodedMemoryArea.get(24, 1), DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(0, 4)
      );
      private static final IEncodedMemoryArea RF = new EncodedMemoryAreaList(
         DirectEncodedMemoryArea.get(28, 1), DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(0, 4)
      );

      private static IEncodedMemoryArea q(int var0) {
         return var0 == 16 ? RF : q;
      }

      @Override
      public CW buildOperand(byte[] var1, int var2) {
         int var3 = DirectEncodedMemoryArea.get(5, 1).decodeInt(var1);
         int var4 = var1[2] & 15;
         if (var3 == 0 && var4 == 15) {
            return new ub(32, q(var2)).buildOperand(var1, var2);
         } else {
            int var5 = k.RF(var1, q(var2));
            return CW.q(64, k.q(var3, var4, (long)var5));
         }
      }
   }
}
