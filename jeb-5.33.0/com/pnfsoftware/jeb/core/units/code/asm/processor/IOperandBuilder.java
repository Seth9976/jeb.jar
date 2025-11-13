package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IOperandBuilder {
   IInstructionOperand buildOperand(byte[] var1, int var2) throws ProcessorException;
}
