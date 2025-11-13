package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public final class cte extends AbstractInstructionOperandGeneric implements cqj {
   public static final int q = 4097;

   public cte(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   public int xK() {
      if (this.getOperandType() != 4097) {
         throw new IllegalStateException();
      } else {
         return (int)(this.getOperandValue() >> 32) & 65535;
      }
   }

   public int Dw() {
      if (this.getOperandType() != 4097) {
         throw new IllegalStateException();
      } else {
         return (int)this.getOperandValue();
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return ctf.Uv(var1);
   }

   @Override
   public boolean q() {
      return this.type == 5 || this.type == 4;
   }

   @Override
   public boolean RF() {
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

   @Override
   public long q(ctc var1) {
      return ctf.q(var1, this);
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      CharSequence var4 = super.formatOperand(var1, var2);
      return ctf.q((ctc)var1, this, var4);
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return ctf.RF((ctc)var1, this);
   }
}
