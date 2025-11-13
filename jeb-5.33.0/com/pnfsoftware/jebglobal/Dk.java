package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessorInformation;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorInformation;

class Dk implements IMachineContext {
   Dk(RE var1, LD var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public IRegisterData getRegisters() {
      return this.pC;
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.A.UT.z();
   }

   @Override
   public IProcessorInformation getInformation() {
      return new ProcessorInformation(this.A.UT.ys());
   }
}
