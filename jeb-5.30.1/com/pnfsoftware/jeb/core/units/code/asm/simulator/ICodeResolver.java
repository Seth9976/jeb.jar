package com.pnfsoftware.jeb.core.units.code.asm.simulator;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public interface ICodeResolver {
   ICodePointer determineNextAddress(IMachineContext var1, IInstruction var2) throws ProcessorException;

   IResolvedOperandValue determineOperandValue(IMachineContext var1, IInstruction var2, int var3) throws ProcessorException;
}
