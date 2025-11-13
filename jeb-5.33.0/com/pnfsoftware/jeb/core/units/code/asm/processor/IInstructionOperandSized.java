package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public interface IInstructionOperandSized extends IInstructionOperand {
   int getOperandBitsize();
}
