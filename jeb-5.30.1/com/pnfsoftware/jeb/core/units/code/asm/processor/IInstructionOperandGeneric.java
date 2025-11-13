package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IInstructionOperandGeneric extends IInstructionOperandRegisterBased, IInstructionOperandSized {
   int TYPE_REG = 0;
   int TYPE_IMM = 1;
   int TYPE_ADDR = 2;
   int TYPE_RELADDR = 3;
   int TYPE_MEMREG = 4;
   int TYPE_MEMIMM = 5;
   int TYPE_ALIAS = 6;
   int TYPE_LIST = 7;
   int TYPE_CMA = 8;
   int TYPE_SIMM = 9;
   int TYPE_USER_1 = 4096;

   int getOperandType();

   long getOperandValue();

   long getOperandValue(long var1);

   String getPrefix(IInstruction var1);

   String getSuffix(IInstruction var1);

   String getAlias(long var1);

   default boolean isRegister() {
      return this.getOperandType() == 0;
   }

   default boolean isImmediate() {
      return this.getOperandType() == 1;
   }
}
