package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public class azk {
   @SerId(1)
   byte[] q;
   @SerId(2)
   int RF;

   public azk() {
   }

   public azk(int var1) {
      this.RF = var1;
   }

   public azk(azk var1) {
      this.RF = var1.RF;
      if (var1.q != null) {
         this.q = Arrays.copyOf(var1.q, var1.q.length);
      }
   }

   byte[] q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("MemoryPage [data_size=");
      var1.append(this.q != null ? this.q.length : 0);
      var1.append(", protection=");
      var1.append((this.RF & 1) != 0 ? "R" : "_");
      var1.append((this.RF & 2) != 0 ? "W" : "_");
      var1.append((this.RF & 4) != 0 ? "X]" : "_]");
      return var1.toString();
   }
}
