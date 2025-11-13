package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.util.format.Strings;

public class ej implements Nt {
   public static final String q = "DISCARD";
   private final int RF;

   public ej(int var1) {
      this.RF = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", "DISCARD", this.RF);
   }

   public int q() {
      return this.RF;
   }
}
