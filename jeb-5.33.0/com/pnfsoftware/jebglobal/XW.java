package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

interface XW {
   IX pC = new XW.Av('D', 1);
   IX A = new XW.Av('S', 2);
   IX kS = new XW.Av('H', 2);
   IX wS = new XW.Av('H', 4);
   IX UT = new XW.Av('B', 8);
   IX E = var0 -> (var0[0] & 64) == 0 ? null : "2D";

   public static class Av implements IX {
      private char Ab;
      private int rl;
      private IEncodedMemoryArea z;

      public Av(char var1, int var2) {
         this(var1, var2, DirectEncodedMemoryArea.get(30, 1));
      }

      public Av(char var1, int var2, IEncodedMemoryArea var3) {
         this.Ab = var1;
         this.rl = var2;
         this.z = var3;
      }

      @Override
      public CharSequence getDataType(byte[] var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append(this.z.decodeInt(var1) == 0 ? this.rl : 2 * this.rl);
         var2.append(this.Ab);
         return var2;
      }
   }
}
