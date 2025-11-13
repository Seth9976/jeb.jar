package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessorInformation;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;

public interface IMachineContext {
   IProcessorInformation getInformation();

   IVirtualMemory getMemory();

   IRegisterData getRegisters();
}
