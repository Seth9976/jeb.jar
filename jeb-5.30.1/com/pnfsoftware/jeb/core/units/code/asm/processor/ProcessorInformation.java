package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.util.io.Endianness;

public class ProcessorInformation implements IProcessorInformation {
   private Endianness endianness;

   public ProcessorInformation(Endianness var1) {
      this.endianness = var1;
   }

   public void setEndianness(Endianness var1) {
      this.endianness = var1;
   }

   @Override
   public Endianness getEndianness() {
      return this.endianness;
   }
}
