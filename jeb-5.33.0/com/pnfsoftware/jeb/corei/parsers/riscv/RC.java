package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class RC extends AbstractInstructionOperandGeneric {
   public RC(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getRegisterName(long var1) {
      return HE.kS.getDescriptionEntryById(var1).getName();
   }
}
