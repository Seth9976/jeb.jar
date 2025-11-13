package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class FastLongSet extends AbstractSet {
   private long from;
   private long to;
   private Bitmap bitmap;

   public FastLongSet(long var1, long var3) {
      if (var1 >= var3) {
         throw new IllegalArgumentException("Illegal set bounds");
      } else {
         long var5 = var3 - var1;
         if (var5 > 2147483647L) {
            throw new IllegalArgumentException("Set range is too wide");
         } else {
            this.from = var1;
            this.to = var3;
            this.bitmap = new Bitmap((int)var5);
         }
      }
   }

   public FastLongSet(long var1) {
      this(0L, var1);
   }

   public FastLongSet(FastLongSet var1) {
      this.from = var1.from;
      this.to = var1.to;
      this.bitmap = var1.bitmap.clone();
   }

   @Override
   public int size() {
      return this.bitmap.countOnes();
   }

   @Override
   public boolean isEmpty() {
      return this.bitmap.isEmpty();
   }

   @Override
   public void clear() {
      this.bitmap.clear();
   }

   public boolean add(Long var1) {
      long var2 = var1;
      if (var2 >= this.from && var2 < this.to) {
         return this.bitmap.set((int)(var2 - this.from), true);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public boolean remove(Object var1) {
      if (!(var1 instanceof Long)) {
         throw new IllegalArgumentException();
      } else {
         long var2 = (Long)var1;
         if (var2 >= this.from && var2 < this.to) {
            return this.bitmap.set((int)(var2 - this.from), false);
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   @Override
   public boolean contains(Object var1) {
      if (!(var1 instanceof Long)) {
         throw new IllegalArgumentException();
      } else {
         long var2 = (Long)var1;
         if (var2 >= this.from && var2 < this.to) {
            return this.bitmap.get((int)(var2 - this.from));
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   @Override
   public boolean containsAll(Collection var1) {
      if (var1 instanceof FastLongSet var2 && this.from == var2.from && this.to == var2.to) {
         return this.bitmap.equals(var2.bitmap);
      } else {
         for (Object var3 : var1) {
            if (!this.contains(var3)) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean addAll(Collection var1) {
      boolean var2 = false;

      for (Long var4 : var1) {
         if (this.add(var4)) {
            var2 = true;
         }
      }

      return var2;
   }

   public void addAllFast(FastLongSet var1) {
      if (this.from == var1.from && this.to == var1.to) {
         this.bitmap.addAllFrom(var1.bitmap);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public Iterator iterator() {
      return new FastLongSet$1(this);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d values: [", this.size());

      for (long var3 : this) {
         Strings.ff(var1, "0x%X, ", var3);
      }

      Strings.ff(var1, "]");
      return var1.toString();
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof FastLongSet var2 && this.from == var2.from && this.to == var2.to) {
         if (this.bitmap == null) {
            if (var2.bitmap != null) {
               return false;
            }
         } else if (!this.bitmap.equals(var2.bitmap)) {
            return false;
         }

         return true;
      } else {
         return super.equals(var1);
      }
   }
}
