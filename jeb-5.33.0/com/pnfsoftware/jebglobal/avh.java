package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class avh extends auo {
   @SerId(1)
   private long pC;
   @SerId(2)
   private int A;

   public avh(long var1, long var3, int var5) {
      super(0, null);
      this.UT(3);
      this.pC(var1);
      this.pC = var3;
      this.A = var5;
   }

   public long kS() {
      return this.pC;
   }

   public int wS() {
      return this.A;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoImmediate(insn@%X,opndIndex:%d)", this.pC, this.A);
   }
}
