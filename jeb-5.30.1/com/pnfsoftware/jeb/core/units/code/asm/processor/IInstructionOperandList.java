package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IInstructionOperandList extends IInstructionOperandGeneric {
   IInstructionOperand[] getOperands();

   String getSeparator();

   IInstructionOperandGeneric merge(long var1);
}
