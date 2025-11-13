package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class Operand extends AbstractInstructionOperandGeneric {
   public Operand(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getRegisterName(long var1) {
      throw new UnsupportedOperationException("This operand can not be a register");
   }
}
