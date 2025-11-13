package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Bc extends FH {
   @SerId(1)
   private int A;
   @SerId(2)
   private Yg.Sv kS;
   @SerId(3)
   private int wS;

   public Bc(int var1, int var2, int var3, Yg.Sv var4, int var5, Yg... var6) {
      super(var1, var2, var6);
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   @Override
   public Yg[] pC() {
      return new Yg[0];
   }

   @Override
   public IInstructionOperandGeneric merge(long var1) {
      int var3 = this.A & 65536;
      return Yg.pC(this.wS, this.kS, this.value, var3);
   }

   public Yg A() {
      return this.E()[0];
   }

   public Yg kS() {
      return this.E()[1];
   }
}
