package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cnm extends AbstractInstructionOperandGeneric {
   public cnm(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getRegisterName(long var1) {
      return cnp.Dw.getDescriptionEntryById(var1).getName();
   }
}
