package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class Bitmap {
   @SerId(1)
   private int bitsize;
   @SerId(2)
   private long[] array;

   public Bitmap(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal size for bitmap: " + var1);
      } else {
         this.bitsize = var1;
         this.array = new long[(var1 + 63) / 64];
      }
   }

   public Bitmap clone() {
      Bitmap var1 = new Bitmap(this.bitsize);
      System.arraycopy(this.array, 0, var1.array, 0, this.array.length);
      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.bitsize;
      return 31 * var1 + Arrays.hashCode(this.array);
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
         Bitmap var2 = (Bitmap)var1;
         return this.bitsize != var2.bitsize ? false : Arrays.equals(this.array, var2.array);
      }
   }

   public int size() {
      return this.bitsize;
   }

   public int countOnes() {
      int var1 = 0;
      long[] var2 = this.array;
      int var3 = var2.length;

      for (int var4 = 0; var4 < var3; var4++) {
         for (long var5 = var2[var4]; var5 != 0L; var5 >>>= 1) {
            if ((var5 & 1L) != 0L) {
               var1++;
            }
         }
      }

      return var1;
   }

   public int countZeros() {
      return this.size() - this.countOnes();
   }

   public void clear() {
      for (int var1 = 0; var1 < this.array.length; var1++) {
         this.array[var1] = 0L;
      }
   }

   public boolean set(int var1, boolean var2) {
      if (var1 >= 0 && var1 < this.bitsize) {
         long var3 = this.array[var1 >> 6];
         long var5 = 1L << (int)(var1 & 63L);
         boolean var7 = (var3 & var5) != 0L;
         if (!(var7 ^ var2)) {
            return false;
         } else {
            if (var2) {
               this.array[var1 >> 6] = var3 | var5;
            } else {
               this.array[var1 >> 6] = var3 & ~var5;
            }

            return true;
         }
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public void set(int var1) {
      if (var1 >= 0 && var1 < this.bitsize) {
         this.array[var1 >> 6] = this.array[var1 >> 6] | 1L << (int)(var1 & 63L);
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public void setRange(int var1, int var2) {
      for (int var3 = var1; var3 < var2; var3++) {
         this.set(var3);
      }
   }

   public void reset(int var1) {
      if (var1 >= 0 && var1 < this.bitsize) {
         this.array[var1 >> 6] = this.array[var1 >> 6] & ~(1L << (int)(var1 & 63L));
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public boolean get(int var1) {
      if (var1 >= 0 && var1 < this.bitsize) {
         return (this.array[var1 >> 6] & 1L << (int)(var1 & 63L)) != 0L;
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public void addAllFrom(Bitmap var1) {
      if (this.bitsize != var1.bitsize) {
         throw new IllegalArgumentException();
      } else {
         for (int var2 = 0; var2 < this.array.length; var2++) {
            this.array[var2] = this.array[var2] | var1.array[var2];
         }
      }
   }

   public void removeAllFrom(Bitmap var1) {
      if (this.bitsize != var1.bitsize) {
         throw new IllegalArgumentException();
      } else {
         for (int var2 = 0; var2 < this.array.length; var2++) {
            this.array[var2] = this.array[var2] & ~var1.array[var2];
         }
      }
   }

   public boolean isFull() {
      int var1 = this.bitsize & 63;
      if (var1 == 0) {
         for (int var7 = 0; var7 < this.array.length; var7++) {
            if (this.array[var7] != -1L) {
               return false;
            }
         }

         return true;
      } else {
         for (int var2 = 0; var2 < this.array.length - 1; var2++) {
            if (this.array[var2] != -1L) {
               return false;
            }
         }

         long var6 = this.array[this.array.length - 1];
         long var4 = (1L << var1) - 1L;
         return var6 == var4;
      }
   }

   public boolean isEmpty() {
      for (long var4 : this.array) {
         if (var4 != 0L) {
            return false;
         }
      }

      return true;
   }

   public List ones() {
      ArrayList var1 = new ArrayList();
      int var3 = 0;

      for (long var7 : this.array) {
         for (int var2 = var3 << 6; var7 != 0L; var2++) {
            if ((var7 & 1L) != 0L) {
               var1.add(var2);
            }

            var7 >>>= 1;
         }

         var3++;
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < this.bitsize; var2++) {
         var1.append(this.get(var2) ? "1" : "0");
      }

      return var1.toString();
   }

   public String formatAsRanges() {
      return this.formatAsRanges(true);
   }

   public String formatAsRanges(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = -1;

      for (int var4 = 0; var4 < this.bitsize; var4++) {
         boolean var5 = this.get(var4);
         if (var3 < 0 && var5) {
            var3 = var4;
         } else if (var3 >= 0 && !var5) {
            if (var2.length() > 0) {
               var2.append("U");
            }

            if (var3 + 1 == var4) {
               Strings.ff(var2, "[%d]", var3);
            } else if (var1) {
               Strings.ff(var2, "[%d,%d)", var3, var4);
            } else {
               Strings.ff(var2, "[%d,%d]", var3, var4 - 1);
            }

            var3 = -1;
         }
      }

      return var2.length() == 0 ? "[]" : var2.toString();
   }
}
