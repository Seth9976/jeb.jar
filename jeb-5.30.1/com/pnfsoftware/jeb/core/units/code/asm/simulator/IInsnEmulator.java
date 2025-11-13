package com.pnfsoftware.jeb.core.units.code.asm.simulator;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public interface IInsnEmulator {
   boolean isPCUpdated();

   Long compute(byte[] var1, long var2, int var4, IInstructionOperand[] var5, IMachineContext var6) throws ProcessorException;

   IInsnEmulator.BranchType getBranchType();

   public static enum BranchType {
      CALL,
      JMP;
   }
}
