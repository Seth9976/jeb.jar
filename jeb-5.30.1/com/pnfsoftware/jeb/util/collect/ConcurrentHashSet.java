package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class ConcurrentHashSet implements Set {
   private static Object MOOT_VALUE = new Object();
   @SerId(1)
   private ConcurrentHashMap map = new ConcurrentHashMap();

   public ConcurrentHashSet() {
      this.map = new ConcurrentHashMap();
   }

   public ConcurrentHashSet(int var1) {
      this.map = new ConcurrentHashMap(var1);
   }

   public ConcurrentHashSet(Collection var1) {
      this.map = new ConcurrentHashMap(var1.size());
      this.addAll(var1);
   }

   @Override
   public int hashCode() {
      return this.map.keySet().hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.map.keySet().equals(var1);
   }

   @Override
   public void clear() {
      this.map.clear();
   }

   @Override
   public int size() {
      return this.map.size();
   }

   @Override
   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   @Override
   public boolean contains(Object var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public boolean add(Object var1) {
      return this.map.put(var1, MOOT_VALUE) == null;
   }

   @Override
   public boolean remove(Object var1) {
      return this.map.remove(var1) != null;
   }

   @Override
   public boolean addAll(Collection var1) {
      boolean var2 = false;

      for (Object var4 : var1) {
         var2 |= this.add(var4);
      }

      return var2;
   }

   @Override
   public boolean removeAll(Collection var1) {
      boolean var2 = false;

      for (Object var4 : var1) {
         var2 |= this.remove(var4);
      }

      return var2;
   }

   @Override
   public boolean containsAll(Collection var1) {
      for (Object var3 : var1) {
         if (!this.map.containsKey(var3)) {
            return false;
         }
      }

      return true;
   }

   @Override
   public boolean retainAll(Collection var1) {
      boolean var2 = false;
      Iterator var3 = this.iterator();

      while (var3.hasNext()) {
         if (!var1.contains(var3.next())) {
            var3.remove();
            var2 = true;
         }
      }

      return var2;
   }

   @Override
   public Iterator iterator() {
      return this.map.keySet().iterator();
   }

   @Override
   public Object[] toArray() {
      return this.map.keySet().toArray();
   }

   @Override
   public Object[] toArray(Object[] var1) {
      return this.map.keySet().toArray(var1);
   }

   @Override
   public String toString() {
      return this.map.keySet().toString();
   }
}
