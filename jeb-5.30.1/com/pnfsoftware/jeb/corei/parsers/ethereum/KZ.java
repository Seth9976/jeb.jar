package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;

@Ser
public class KZ implements IInstructionOperand {
   @SerId(1)
   BigInteger q;

   public KZ(BigInteger var1) {
      this.q = var1;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      return "0x" + this.q.toString(16).toUpperCase();
   }
}
