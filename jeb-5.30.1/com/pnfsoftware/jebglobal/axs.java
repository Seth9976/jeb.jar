package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class axs extends axh {
   public axs(long var1, long var3) {
      super(var1, var3);
      this.Uv(3);
   }

   public static String RF(long var0) {
      return Strings.ff("gap_%Xh", var0);
   }

   public bbx cC() {
      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("GapItem@%X(->%X)", this.getMemoryAddress(), this.getMemoryAddress() + this.getMemorySize());
   }
}
