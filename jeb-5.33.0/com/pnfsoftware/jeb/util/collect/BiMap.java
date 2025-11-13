package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

@Ser
public class BiMap {
   @SerId(1)
   private Map map;
   @SerId(2)
   private Map rmap;

   public BiMap() {
      this(CollectionOrder.NONE);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public BiMap(CollectionOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("Illegal key order");
      } else {
         switch (var1) {
            case NONE:
               this.map = new HashMap();
               this.rmap = new HashMap();
               break;
            case INSERTION:
               this.map = new LinkedHashMap();
               this.rmap = new LinkedHashMap();
               break;
            case NATURAL:
               this.map = new TreeMap();
               this.rmap = new TreeMap();
               break;
            default:
               throw new RuntimeException();
         }
      }
   }

   public void clear() {
      this.map.clear();
      this.rmap.clear();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public int size() {
      int var1 = this.map.size();
      if (this.rmap.size() != var1) {
         throw new IllegalStateException();
      } else {
         return var1;
      }
   }

   public Object put(Object var1, Object var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Illegal null key");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Illegal null key");
      } else {
         Object var3 = this.map.put(var1, var2);
         if (var3 != null) {
            this.rmap.remove(var3);
         }

         Object var4 = this.rmap.put(var2, var1);
         if (var4 != null) {
            this.map.remove(var4);
         }

         return var3;
      }
   }

   public void putAll(Map var1) {
      for (Entry var3 : var1.entrySet()) {
         this.put(var3.getKey(), var3.getValue());
      }
   }

   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public boolean containsValue(Object var1) {
      return this.rmap.containsKey(var1);
   }

   public Object get(Object var1) {
      return this.map.get(var1);
   }

   public Object getKeyForValue(Object var1) {
      return this.rmap.get(var1);
   }

   public Object remove(Object var1) {
      Object var2 = this.map.remove(var1);
      if (var2 != null) {
         this.rmap.remove(var2);
      }

      return var2;
   }

   public Object removeValue(Object var1) {
      Object var2 = this.rmap.remove(var1);
      if (var2 != null) {
         this.map.remove(var2);
      }

      return var2;
   }

   public Set keySet() {
      return this.map.keySet();
   }

   public Set values() {
      return this.rmap.keySet();
   }

   public Map asMap() {
      return Collections.unmodifiableMap(this.map);
   }

   public Map asReverseMap() {
      return Collections.unmodifiableMap(this.rmap);
   }

   @Override
   public String toString() {
      return this.map.toString();
   }
}
