package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayd extends axj implements axy {
   @SerId(1)
   private long q;
   @SerId(2)
   private int RF;

   public ayd(long var1, long var3, int var5) {
      super(0, null);
      this.Uv(3);
      this.q(var1);
      this.q = var3;
      this.RF = var5;
   }

   public long xK() {
      return this.q;
   }

   public int Dw() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoImmediate(insn@%X,opndIndex:%d)", this.q, this.RF);
   }
}
