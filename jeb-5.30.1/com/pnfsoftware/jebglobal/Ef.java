package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

@FunctionalInterface
public interface Ef extends IOperandBuilder {
   CW buildOperand(byte[] var1, int var2) throws ProcessorException;

   default String xK(byte[] var1) {
      return null;
   }
}
