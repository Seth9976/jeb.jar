package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@Ser
public final class ArrayList1 extends AbstractList implements Cloneable, List, RandomAccess {
   @SerId(1)
   private int size0;
   @SerId(2)
   private Object entry0;
   @SerId(3)
   private List array;

   @SerConstructor
   public ArrayList1() {
   }

   public ArrayList1(Object var1) {
      this.add(var1);
   }

   @Override
   public int size() {
      return this.array != null ? this.array.size() : this.size0;
   }

   @Override
   public Object get(int var1) {
      if (this.array != null) {
         return this.array.get(var1);
      } else if (var1 == 0 && this.size0 == 1) {
         return this.entry0;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public Object set(int var1, Object var2) {
      if (this.array != null) {
         return this.array.set(var1, var2);
      } else if (var1 == 0 && this.size0 == 1) {
         Object var3 = this.entry0;
         this.entry0 = var2;
         return var3;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public void add(int var1, Object var2) {
      if (this.array != null) {
         this.array.add(var1, var2);
      } else if (this.size0 == 0) {
         if (var1 == 0) {
            this.entry0 = var2;
            this.size0 = 1;
         } else {
            throw new IndexOutOfBoundsException();
         }
      } else {
         ArrayList var3 = new ArrayList(2);
         var3.add(this.entry0);
         var3.add(var1, var2);
         this.array = var3;
         this.entry0 = null;
         this.size0 = 0;
      }
   }

   @Override
   public Object remove(int var1) {
      if (this.array != null) {
         Object var3 = this.array.remove(var1);
         if (this.array.size() == 1) {
            this.entry0 = this.array.get(0);
            this.size0 = 1;
            this.array = null;
         }

         return var3;
      } else if (var1 == 0 && this.size0 == 1) {
         Object var2 = this.entry0;
         this.entry0 = null;
         this.size0 = 0;
         return var2;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
