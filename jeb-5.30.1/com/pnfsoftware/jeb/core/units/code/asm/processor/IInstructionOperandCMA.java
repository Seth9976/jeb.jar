package com.pnfsoftware.jeb.core.units.code.asm.processor;

public interface IInstructionOperandCMA extends IInstructionOperandGeneric {
   long getMemoryBaseRegister();

   int getMemoryScale();

   long getMemoryIndexRegister();

   long getMemoryDisplacement();
}
