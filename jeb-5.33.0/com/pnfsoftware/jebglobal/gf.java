package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

class gf implements Hu {
   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = MJ.pC(var1);
      return (var3 == 3 ? sQ.UT : sQ.eP).buildOperand(var1, var2);
   }
}
