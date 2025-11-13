package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@Ser
public class ReferenceCounter {
   @SerId(1)
   private Map map = new HashMap();

   public synchronized void clear() {
      this.map.clear();
   }

   public synchronized boolean isEmpty() {
      return this.map.isEmpty();
   }

   public synchronized int size() {
      return this.map.size();
   }

   public synchronized int getCount() {
      int var1 = 0;

      for (int var3 : this.map.values()) {
         var1 += var3;
      }

      return var1;
   }

   public synchronized int inc(Object var1) {
      Integer var2 = (Integer)this.map.get(var1);
      if (var2 == null) {
         var2 = 1;
      } else {
         var2 = var2 + 1;
         if (var2 < 0) {
            throw new IllegalStateException("Counter overflow");
         }
      }

      this.map.put(var1, var2);
      return var2;
   }

   public synchronized int dec(Object var1) {
      Integer var2 = (Integer)this.map.get(var1);
      if (var2 == null) {
         throw new IllegalArgumentException("Key is not present");
      } else if (var2 <= 0) {
         throw new RuntimeException("Count is <= 0");
      } else {
         var2 = var2 - 1;
         if (var2 == 0) {
            this.map.remove(var1);
         } else {
            this.map.put(var1, var2);
         }

         return var2;
      }
   }

   public synchronized boolean has(Object var1) {
      return this.map.containsKey(var1);
   }

   public synchronized int get(Object var1) {
      Integer var2 = (Integer)this.map.get(var1);
      return var2 == null ? 0 : var2;
   }

   public synchronized Map getAsMap() {
      return Collections.unmodifiableMap(this.map);
   }

   public synchronized boolean remove(Object var1) {
      return this.map.remove(var1) != null;
   }

   public synchronized void load(Collection var1) {
      for (Object var3 : var1) {
         this.inc(var3);
      }
   }

   public synchronized Set keys() {
      return new HashSet(this.map.keySet());
   }

   public synchronized Map copyToMap() {
      return new HashMap(this.map);
   }

   @Override
   public synchronized String toString() {
      return this.map.toString();
   }

   public synchronized String formatAllReferences() {
      return this.formatTopReferences(-1);
   }

   public synchronized String formatTopReferences(int var1) {
      ArrayList var2 = new ArrayList(this.map.entrySet());
      Collections.sort(var2, new ReferenceCounter$1(this));
      int var3 = var1 < 0 ? var2.size() : Math.min(var1, var2.size());
      int var4 = 0;
      StringBuilder var5 = new StringBuilder();

      for (Entry var7 : var2.subList(0, var3)) {
         Strings.ff(var5, "- %d: %s\n", var7.getValue(), var7.getKey());
         var4 += var7.getValue();
      }

      int var9 = var4;

      for (Entry var8 : var2.subList(var3, var2.size())) {
         var9 += var8.getValue();
      }

      double var11 = var9 == 0 ? 0.0 : (double)var4 / var9 * 100.0;
      if (var1 >= 0) {
         var5.insert(0, Strings.ff("%d/%d top entries (%.2f%% of %d)\n", var3, var2.size(), var11, var9));
      }

      return var5.toString();
   }
}
