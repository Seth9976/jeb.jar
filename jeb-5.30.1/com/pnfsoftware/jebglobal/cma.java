package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public interface cma {
   String q(byte[] var1);

   IInstructionOperand[] q(byte[] var1, int var2) throws ProcessorException;

   clt q();
}
