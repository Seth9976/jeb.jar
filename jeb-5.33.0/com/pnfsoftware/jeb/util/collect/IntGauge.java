package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public class IntGauge {
   private int base;
   private byte[] records;
   private int remaining;

   public IntGauge(int var1) {
      this(0, var1);
   }

   public IntGauge(int var1, int var2) {
      int var3 = var2 - var1;
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.base = var1;
         this.records = new byte[var3];
         this.remaining = var3;
      }
   }

   public void clear() {
      for (int var1 = 0; var1 < this.records.length; var1++) {
         this.records[var1] = 0;
      }

      this.remaining = this.records.length;
   }

   public int getBegin() {
      return this.base;
   }

   public int getEnd() {
      return this.base + this.records.length;
   }

   public boolean record(int var1, int var2) {
      int var3 = var2 - var1;
      if (var3 >= 0 && var1 >= this.base && var2 <= this.base + this.records.length) {
         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = var1 - this.base + var4;
            if (this.records[var5] != 0) {
               return false;
            }

            this.records[var5] = 1;
            this.remaining--;
         }

         return true;
      } else {
         return false;
      }
   }

   public int getRemaining() {
      return this.remaining;
   }

   public boolean isComplete() {
      return this.remaining == 0;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.base;
      return 31 * var1 + Arrays.hashCode(this.records);
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
         IntGauge var2 = (IntGauge)var1;
         return this.base != var2.base ? false : Arrays.equals(this.records, var2.records);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("[%d-%d):%s%s", this.base, this.base + this.records.length, Arrays.toString(this.records), this.isComplete() ? "->COMPLETED" : "");
   }
}
