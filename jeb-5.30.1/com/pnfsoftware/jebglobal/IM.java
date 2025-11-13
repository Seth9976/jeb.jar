package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class IM extends yS {
   @SerId(1)
   private int gP;
   @SerId(2)
   private CW.CU za;
   @SerId(3)
   private int lm;

   public IM(int var1, int var2, int var3, CW.CU var4, int var5, CW... var6) {
      super(var1, var2, var6);
      this.gP = var3;
      this.za = var4;
      this.lm = var5;
   }

   @Override
   public CW[] q() {
      return new CW[0];
   }

   @Override
   public IInstructionOperandGeneric merge(long var1) {
      int var3 = this.gP & 65536;
      return CW.q(this.lm, this.za, this.value, var3);
   }

   public CW RF() {
      return this.oW()[0];
   }

   public CW xK() {
      return this.oW()[1];
   }
}
