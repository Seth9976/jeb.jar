package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

interface kE {
   Dm q = new kE.eo('D', 1);
   Dm RF = new kE.eo('S', 2);
   Dm xK = new kE.eo('H', 2);
   Dm Dw = new kE.eo('H', 4);
   Dm Uv = new kE.eo('B', 8);
   Dm oW = var0 -> (var0[0] & 64) == 0 ? null : "2D";

   public static class eo implements Dm {
      private char xW;
      private int KT;
      private IEncodedMemoryArea Gf;

      public eo(char var1, int var2) {
         this(var1, var2, DirectEncodedMemoryArea.get(30, 1));
      }

      public eo(char var1, int var2, IEncodedMemoryArea var3) {
         this.xW = var1;
         this.KT = var2;
         this.Gf = var3;
      }

      @Override
      public CharSequence getDataType(byte[] var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append(this.Gf.decodeInt(var1) == 0 ? this.KT : 2 * this.KT);
         var2.append(this.xW);
         return var2;
      }
   }
}
