package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class hf {
   public int q;
   public byte RF;

   public hf(int var1, byte var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("@%d:%c", this.q, this.RF);
   }
}
