package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.util.format.Strings;

public class Ws implements cq {
   private final int pC;

   public Ws(int var1) {
      this.pC = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", "DISCARD", this.pC);
   }
}
