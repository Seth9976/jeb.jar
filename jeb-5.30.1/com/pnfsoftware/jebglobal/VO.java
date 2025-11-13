package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class VO {
   public byte q;
   public long RF;

   public static VO q(Hv var0) throws IOException {
      byte var1 = var0.RF();
      long var2 = var0.gO();
      return new VO(var1, var2);
   }

   public VO(byte var1, long var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
      return 31 * var1 + this.q;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         VO var2 = (VO)var1;
         return this.RF != var2.RF ? false : this.q == var2.q;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%d,oid=%Xh", this.q, this.RF);
   }
}
