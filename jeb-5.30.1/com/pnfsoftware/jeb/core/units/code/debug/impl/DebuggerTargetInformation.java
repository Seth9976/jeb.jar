package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;

public class DebuggerTargetInformation implements IDebuggerTargetInformation {
   ProcessorType processorType;
   Endianness endianness;
   String description;

   public DebuggerTargetInformation(ProcessorType var1, Endianness var2) {
      this.processorType = var1;
      this.endianness = var2;
   }

   @Override
   public ProcessorType getProcessorType() {
      return this.processorType;
   }

   @Override
   public Endianness getEndianness() {
      return this.endianness;
   }

   @Override
   public String getStringDescription() {
      return this.description;
   }

   public void setStringDescription(String var1) {
      this.description = var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "Debuggee is running on %s", Strings.safe(this.getProcessorType(), "?"));
      if (this.getEndianness() != null) {
         Strings.ff(var1, " (%s)", this.getEndianness());
      }

      if (this.getStringDescription() != null) {
         var1.append("\n" + this.getStringDescription());
      }

      return var1.toString();
   }
}
