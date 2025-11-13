package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.format.Strings;

public class DecompilationResult {
   public String error;
   public int nsize;
   public int bsize;
   public long time1;
   public double speedi1;
   public long time2;
   public double speedi2;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.nsize != 0) {
         Strings.ff(var1, "n=%d/", this.nsize);
      }

      if (this.bsize != 0) {
         Strings.ff(var1, "b=%d/", this.bsize);
      }

      if (this.time1 != 0L) {
         Strings.ff(var1, "t=%d/", this.time1);
      }

      if (this.speedi1 != 0.0) {
         Strings.ff(var1, "v=%f/", this.speedi1);
      }

      if (!Strings.isBlank(this.error)) {
         Strings.ff(var1, "FAILED!");
      }

      return var1.toString();
   }
}
