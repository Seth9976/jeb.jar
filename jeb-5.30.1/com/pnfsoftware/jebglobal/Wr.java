package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class Wr implements Ef {
   @Override
   public CW buildOperand(byte[] var1, int var2) {
      int var3 = wS.xK(var1);
      return var3 == 0 ? null : new jO(DirectEncodedMemoryArea.get(16, 7), var3).buildOperand(var1, var2);
   }
}
