package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Predicate;

@Ser
public class MultiList {
   @SerId(1)
   private List list = new ArrayList();
   @SerId(2)
   private int cachedSize = 0;

   public void clear() {
      this.list.clear();
      this.cachedSize = 0;
   }

   public boolean isEmpty() {
      return this.cachedSize == 0;
   }

   public int size() {
      return this.cachedSize;
   }

   public int put(int var1, Object var2) {
      while (this.list.size() <= var1) {
         this.list.add(null);
      }

      Object var3 = (List)this.list.get(var1);
      if (var3 == null) {
         var3 = new ArrayList();
         this.list.set(var1, var3);
      }

      var3.add(var2);
      this.cachedSize++;
      return var3.size();
   }

   public void putMulti(int var1, Collection var2) {
      for (Object var4 : var2) {
         this.put(var1, var4);
      }
   }

   public void putAll(List var1) {
      int var2 = 0;

      for (List var4 : var1) {
         if (var4 != null) {
            for (Object var6 : var4) {
               this.put(var2, var6);
            }
         }

         var2++;
      }
   }

   public List get(int var1) {
      List var2 = null;
      if (var1 >= 0 && var1 < this.list.size()) {
         var2 = (List)this.list.get(var1);
      }

      return var2 != null ? Collections.unmodifiableList(var2) : Collections.emptyList();
   }

   public List clear(int var1) {
      List var2 = null;
      if (var1 >= 0 && var1 < this.list.size()) {
         var2 = (List)this.list.get(var1);
      }

      if (var2 != null) {
         this.list.set(var1, null);
         this.cachedSize = this.cachedSize - var2.size();
      } else {
         var2 = Collections.emptyList();
      }

      return var2;
   }

   public List remove(int var1) {
      List var2 = null;
      if (var1 >= 0 && var1 < this.list.size()) {
         var2 = (List)this.list.remove(var1);
         if (var2 != null) {
            this.cachedSize = this.cachedSize - var2.size();
         }
      }

      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      return var2;
   }

   public boolean removeElement(int var1, Object var2) {
      List var3 = null;
      if (var1 >= 0 && var1 < this.list.size()) {
         var3 = (List)this.list.get(var1);
      }

      if (var3 != null && var3.remove(var2)) {
         this.cachedSize = this.cachedSize - var3.size();
         return true;
      } else {
         return false;
      }
   }

   public int removeMultipleElements(int var1, Predicate var2) {
      int var3 = 0;
      if (var1 >= 0 && var1 < this.list.size()) {
         List var4 = (List)this.list.get(var1);
         if (var4 != null) {
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
               Object var6 = var5.next();
               if (var2.test(var6)) {
                  var5.remove();
                  var3++;
               }
            }
         }
      }

      this.cachedSize -= var3;
      return var3;
   }

   public Object findFirstElement(int var1, Predicate var2) {
      if (var1 >= 0 && var1 < this.list.size()) {
         List var3 = (List)this.list.get(var1);
         if (var3 != null) {
            for (Object var5 : var3) {
               if (var2.test(var5)) {
                  return var5;
               }
            }
         }
      }

      return null;
   }

   public List findAllElements(int var1, Predicate var2) {
      ArrayList var3 = new ArrayList();
      if (var1 >= 0 && var1 < this.list.size()) {
         List var4 = (List)this.list.get(var1);
         if (var4 != null) {
            for (Object var6 : var4) {
               if (var2.test(var6)) {
                  var3.add(var6);
               }
            }
         }
      }

      return var3;
   }

   public Collection values() {
      ArrayList var1 = new ArrayList();

      for (List var3 : this.list) {
         if (var3 != null) {
            var1.addAll(var3);
         }
      }

      return Collections.unmodifiableList(var1);
   }

   public NavigableSet indexSet() {
      TreeSet var1 = new TreeSet();
      int var2 = 0;

      for (List var4 : this.list) {
         if (var4 != null) {
            var1.add(var2);
         }

         var2++;
      }

      return var1;
   }

   @Override
   public String toString() {
      return this.list.toString();
   }
}
