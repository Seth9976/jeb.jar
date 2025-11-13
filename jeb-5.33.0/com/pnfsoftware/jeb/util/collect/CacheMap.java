package com.pnfsoftware.jeb.util.collect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class CacheMap {
   private int maxSize;
   private int evictSize;
   private Map map;
   private LinkedHashSet accesscoll;

   public CacheMap(int var1, int var2, boolean var3) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else if (var2 > 0 && var2 <= var1) {
         this.maxSize = var1;
         this.evictSize = var2;
         if (var3) {
            this.map = new TreeMap();
         } else {
            this.map = new HashMap();
         }

         this.accesscoll = new LinkedHashSet();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public CacheMap(int var1, int var2) {
      this(var1, var2, false);
   }

   public CacheMap(int var1) {
      this(var1, var1 / 4);
   }

   private void verifyConsistency() {
      if (this.map.size() != this.accesscoll.size()) {
         throw new IllegalStateException("Invalid CacheMap state!");
      }
   }

   private void evict() {
      Iterator var1 = this.accesscoll.iterator();

      for (int var2 = 0; var2 < this.evictSize; var2++) {
         if (!var1.hasNext()) {
            return;
         }

         Object var3 = var1.next();
         this.map.remove(var3);
         var1.remove();
      }
   }

   private void accessEntry(Object var1) {
      if (!this.accesscoll.add(var1)) {
         this.accesscoll.remove(var1);
         this.accesscoll.add(var1);
      }
   }

   public int size() {
      return this.map.size();
   }

   public synchronized void clear() {
      this.map.clear();
      this.accesscoll.clear();
   }

   public synchronized Object put(Object var1, Object var2) {
      if (this.map.size() >= this.maxSize) {
         this.evict();
      }

      this.accessEntry(var1);
      return this.map.put(var1, var2);
   }

   public synchronized Object get(Object var1) {
      if (!this.accesscoll.contains(var1)) {
         return null;
      } else {
         this.accessEntry(var1);
         return this.map.get(var1);
      }
   }

   public synchronized boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public synchronized Set keys() {
      return (Set)(this.map instanceof TreeMap ? new TreeSet(this.map.keySet()) : new HashSet(this.map.keySet()));
   }

   public synchronized Object remove(Object var1) {
      return this.accesscoll.remove(var1) ? this.map.remove(var1) : null;
   }

   public synchronized void removeRange(Object var1, Object var2) {
      if (!(this.map instanceof TreeMap)) {
         throw new IllegalStateException("removeRange() not supported for unordered CacheMap");
      } else {
         SortedMap var3 = ((TreeMap)this.map).subMap(var1, var2);
         this.accesscoll.removeAll(var3.keySet());
         var3.clear();
      }
   }
}
