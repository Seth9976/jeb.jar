package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class uG {
   public byte pC;
   public long A;

   public static uG pC(AN var0) throws IOException {
      byte var1 = var0.pC();
      long var2 = var0.E();
      return new uG(var1, var2);
   }

   public uG(byte var1, long var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.A ^ this.A >>> 32);
      return 31 * var1 + this.pC;
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
         uG var2 = (uG)var1;
         return this.A != var2.A ? false : this.pC == var2.pC;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%d,oid=%Xh", this.pC, this.A);
   }
}
