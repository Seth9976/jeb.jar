package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IInstructionOperandRegisterBased extends IInstructionOperand {
   String getRegisterName(long var1);
}
