package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.util.format.Strings;

public class vn implements Nt {
   public static final String q = "MERGE_ON";
   private final int RF;

   public vn(int var1) {
      this.RF = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", "MERGE_ON", this.RF);
   }

   public int q() {
      return this.RF;
   }
}
