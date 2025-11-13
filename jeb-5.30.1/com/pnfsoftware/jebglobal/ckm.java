package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ckm extends GenericCodeFormatter {
   @SerId(1)
   private NumberFormatter q = new NumberFormatter();

   public ckm() {
      this.q.setSignedNumber(true);
      this.q.setForcePositiveRenderingForNonBase10(true);
   }

   @Override
   public void setCodeUnit(INativeCodeUnit var1) {
      super.setCodeUnit(var1);
      this.q.setEndianness(this.getEndianness());
   }

   @Override
   public String getRegisterName(long var1) {
      try {
         return clv.q(var1, this.getCodeUnit().getProcessor().getMode());
      } catch (RuntimeException var3) {
         return "unkreg_" + var1;
      }
   }

   @Override
   public NumberFormatter getNumberFormatter(IInstructionOperand var1, boolean var2) {
      NumberFormatter var3 = super.getNumberFormatter(var1, var2);
      clv var4 = (clv)var1;
      boolean var5 = false;
      if (var4.getOperandType() == 1) {
         var5 = var4.q();
         if (var3 != null) {
            var3.setSignedNumber(var5);
            var3.setForcePositiveRenderingForNonBase10(true);
         }
      }

      return var3 == null ? (var5 ? this.q : this.getDefaultNumberFormatter()) : var3;
   }

   public NumberFormatter q() {
      return this.q;
   }
}
