package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessorInformation;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorInformation;

class gx implements IMachineContext {
   gx(JT var1, Ht var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public IRegisterData getRegisters() {
      return this.q;
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.RF.Uv.Ef();
   }

   @Override
   public IProcessorInformation getInformation() {
      return new ProcessorInformation(this.RF.Uv.gP());
   }
}
