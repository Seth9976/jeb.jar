package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class Ry implements Hu {
   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      int var3 = Gh.kS(var1);
      return var3 == 0 ? null : new OC(DirectEncodedMemoryArea.get(16, 7), var3).buildOperand(var1, var2);
   }
}
