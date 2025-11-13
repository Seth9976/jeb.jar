package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

@FunctionalInterface
public interface Hu extends IOperandBuilder {
   Yg buildOperand(byte[] var1, int var2) throws ProcessorException;

   default String kS(byte[] var1) {
      return null;
   }
}
