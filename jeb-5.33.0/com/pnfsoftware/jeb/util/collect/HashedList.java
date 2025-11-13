package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Ser
public class HashedList extends AbstractList {
   @SerId(1)
   private ArrayList list;
   @SerId(2)
   private HashSet set;

   @SerConstructor
   public HashedList() {
      this(10);
   }

   public HashedList(int var1) {
      this.list = new ArrayList(var1);
      this.set = new HashSet(var1);
   }

   public HashedList(Collection var1) {
      this.list = new ArrayList(var1);
      this.set = new HashSet(var1);
   }

   @Override
   public Object get(int var1) {
      return this.list.get(var1);
   }

   @Override
   public int size() {
      return this.list.size();
   }

   @Override
   public Object set(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException();
      } else if (this.set.contains(var2)) {
         throw new IllegalArgumentException();
      } else {
         Object var3 = this.list.set(var1, var2);
         this.set.remove(var3);
         this.set.add(var2);
         return var3;
      }
   }

   @Override
   public void add(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException();
      } else if (this.set.contains(var2)) {
         throw new IllegalArgumentException();
      } else {
         this.list.add(var1, var2);
         this.set.add(var2);
      }
   }

   @Override
   public Object remove(int var1) {
      Object var2 = this.list.remove(var1);
      this.set.remove(var2);
      return var2;
   }

   @Override
   public boolean contains(Object var1) {
      return this.set.contains(var1);
   }

   @Override
   public boolean containsAll(Collection var1) {
      return this.set.containsAll(var1);
   }
}
