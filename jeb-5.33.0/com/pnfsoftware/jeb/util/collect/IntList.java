package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class IntList {
   private static final int SIZE_INCREASE_MULTIPLIER = 2;
   @SerId(1)
   private int initialCapacity;
   @SerId(2)
   private int bucketCapacity;
   @SerId(3)
   private int cnt;
   @SerId(4)
   private int[] list0;
   @SerId(5)
   private List listX;

   public IntList() {
      this(16, 65536);
   }

   public IntList(int var1, int var2) {
      Assert.a(var1 > 0 && var2 >= var1);
      this.initialCapacity = var1;
      this.bucketCapacity = var2;
      this.list0 = new int[var1];
   }

   public int size() {
      return this.cnt;
   }

   public boolean isEmpty() {
      return this.cnt == 0;
   }

   public void clear() {
      this.cnt = 0;
      this.list0 = new int[this.initialCapacity];
      this.listX = null;
   }

   public int get(int var1) {
      if (var1 >= 0 && var1 < this.cnt) {
         int var2 = var1 / this.bucketCapacity;
         int var3 = var1 % this.bucketCapacity;
         int[] var4 = this.getBucket(var2, false);
         return var4[var3];
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public void add(int var1) {
      int var2 = this.cnt / this.bucketCapacity;
      int var3 = this.cnt % this.bucketCapacity;
      int[] var4 = this.getBucket(var2, true);
      if (var3 >= var4.length) {
         var4 = this.increaseBucket(var4);
         this.setBucket(var2, var4);
      }

      var4[var3] = var1;
      this.cnt++;
   }

   public void set(int var1, int var2) {
      if (var1 >= 0 && var1 < this.cnt) {
         int var3 = var1 / this.bucketCapacity;
         int var4 = var1 % this.bucketCapacity;
         int[] var5 = this.getBucket(var3, false);
         var5[var4] = var2;
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   private int[] getBucket(int var1, boolean var2) {
      int[] var3;
      if (var1 == 0) {
         var3 = this.list0;
      } else {
         if (this.listX == null) {
            Assert.a(var1 == 1);
            this.listX = new ArrayList(16);
         }

         if (var1 - 1 == this.listX.size()) {
            Assert.a(var2);
            var3 = new int[this.initialCapacity];
            this.listX.add(var3);
         } else {
            if (this.listX == null || var1 - 1 >= this.listX.size()) {
               throw new RuntimeException();
            }

            var3 = (int[])this.listX.get(var1 - 1);
         }
      }

      return var3;
   }

   private void setBucket(int var1, int[] var2) {
      if (var1 == 0) {
         this.list0 = var2;
      } else if (var1 - 1 == this.listX.size()) {
         this.listX.add(var2);
      } else {
         if (var1 - 1 >= this.listX.size()) {
            throw new RuntimeException();
         }

         this.listX.set(var1 - 1, var2);
      }
   }

   private int[] increaseBucket(int[] var1) {
      int var2 = Math.min(2 * var1.length, this.bucketCapacity);
      return Arrays.copyOf(var1, var2);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("[");
      int var2 = 0;

      for (int var6 : this.list0) {
         if (var2 >= this.cnt) {
            break;
         }

         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(var6);
         var2++;
      }

      if (this.listX != null) {
         for (int[] var10 : this.listX) {
            for (int var8 : var10) {
               if (var2 >= this.cnt) {
                  break;
               }

               if (var2 >= 1) {
                  var1.append(", ");
               }

               var1.append(var8);
               var2++;
            }
         }
      }

      var1.append("]");
      return var1.toString();
   }
}
