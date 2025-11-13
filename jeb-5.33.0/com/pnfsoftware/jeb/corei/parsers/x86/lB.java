package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public final class lB extends AbstractInstructionOperandGeneric implements Av {
   public lB(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   public int kS() {
      if (this.getOperandType() != 4097) {
         throw new IllegalStateException();
      } else {
         return (int)(this.getOperandValue() >> 32) & 65535;
      }
   }

   public int wS() {
      if (this.getOperandType() != 4097) {
         throw new IllegalStateException();
      } else {
         return (int)this.getOperandValue();
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return MG.UT(var1);
   }

   @Override
   public boolean pC() {
      return this.type == 5 || this.type == 4;
   }

   @Override
   public boolean A() {
      return false;
   }

   @Override
   public boolean isRegister() {
      return this.type == 0;
   }

   @Override
   public boolean isImmediate() {
      return this.type == 1;
   }

   public long pC(vh var1) {
      return MG.pC(var1, this);
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      CharSequence var4 = super.formatOperand(var1, var2);
      return MG.pC((vh)var1, this, var4);
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return MG.A((vh)var1, this);
   }
}
