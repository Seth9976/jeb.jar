package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class awn {
   @SerId(1)
   byte[] pC;
   @SerId(2)
   int A;

   public awn() {
   }

   public awn(int var1) {
      this.A = var1;
   }

   public awn(awn var1) {
      this.A = var1.A;
      if (var1.pC != null) {
         this.pC = Arrays.copyOf(var1.pC, var1.pC.length);
      }
   }

   public int pC() {
      return this.A;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("MemoryPage [data_size=");
      var1.append(this.pC != null ? this.pC.length : 0);
      var1.append(", protection=");
      var1.append((this.A & 1) != 0 ? "R" : "_");
      var1.append((this.A & 2) != 0 ? "W" : "_");
      var1.append((this.A & 4) != 0 ? "X]" : "_]");
      return var1.toString();
   }
}
