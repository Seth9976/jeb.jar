package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

@Ser
public class MultiMap {
   @SerId(1)
   private Map map;
   @SerId(2)
   private int cachedSize;

   public MultiMap() {
      this(CollectionOrder.NONE);
   }

   public MultiMap(CollectionOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("Illegal key order");
      } else {
         switch (var1) {
            case NONE:
               this.map = new HashMap();
               break;
            case INSERTION:
               this.map = new LinkedHashMap();
               break;
            case NATURAL:
               this.map = new TreeMap();
               break;
            default:
               throw new RuntimeException();
         }

         this.cachedSize = 0;
      }
   }

   public void clear() {
      this.map.clear();
      this.cachedSize = 0;
   }

   public int keySize() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.cachedSize == 0;
   }

   public int size() {
      return this.cachedSize;
   }

   public int put(Object var1, Object var2) {
      List var3 = Maps.putMulti(this.map, var1, var2);
      this.cachedSize++;
      return var3.size();
   }

   public void putMulti(Object var1, Collection var2) {
      for (Object var4 : var2) {
         this.put(var1, var4);
      }
   }

   public void putAll(Map var1) {
      for (Entry var3 : var1.entrySet()) {
         for (Object var5 : (List)var3.getValue()) {
            this.put(var3.getKey(), var5);
         }
      }
   }

   public boolean createKey(Object var1) {
      List var2 = (List)this.map.get(var1);
      if (var2 != null) {
         return false;
      } else {
         this.map.put(var1, new ArrayList());
         return true;
      }
   }

   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public boolean containsValue(Object var1) {
      for (List var3 : this.map.values()) {
         if (var3.contains(var1)) {
            return true;
         }
      }

      return false;
   }

   public List get(Object var1, boolean var2) {
      List var3 = (List)this.map.get(var1);
      if (var3 != null) {
         return Collections.unmodifiableList(var3);
      } else {
         return var2 ? Collections.emptyList() : null;
      }
   }

   public List get(Object var1) {
      return this.get(var1, false);
   }

   public List getSafe(Object var1) {
      return this.get(var1, true);
   }

   public List remove(Object var1) {
      List var2 = (List)this.map.remove(var1);
      if (var2 != null) {
         this.cachedSize = this.cachedSize - var2.size();
      }

      return var2;
   }

   public Object removeValue(Object var1, Object var2) {
      return this.removeValue(var1, var2, false);
   }

   public Object removeValue(Object var1, Object var2, boolean var3) {
      List var4 = (List)this.map.get(var1);
      if (var4 == null) {
         return null;
      } else if (!var4.remove(var2)) {
         return null;
      } else {
         this.cachedSize--;
         if (var3 && var4.isEmpty()) {
            this.map.remove(var1);
         }

         return var2;
      }
   }

   public int removeMulti(Object var1, Collection var2) {
      return this.removeMulti(var1, var2, false);
   }

   public int removeMulti(Object var1, Collection var2, boolean var3) {
      List var4 = (List)this.map.get(var1);
      if (var2 == null) {
         return 0;
      } else {
         int var5 = 0;

         for (Object var7 : var2) {
            if (var4.remove(var7)) {
               this.cachedSize--;
               var5++;
            }
         }

         if (var3 && var5 > 0 && var2.isEmpty()) {
            this.map.remove(var1);
         }

         return var5;
      }
   }

   public Collection values() {
      ArrayList var1 = new ArrayList();

      for (List var3 : this.map.values()) {
         var1.addAll(var3);
      }

      return Collections.unmodifiableList(var1);
   }

   public Set keySet() {
      return Collections.unmodifiableSet(this.map.keySet());
   }

   public boolean removeAll(Collection var1) {
      if (this.isEmpty()) {
         return false;
      } else {
         for (Object var3 : var1) {
            this.remove(var3);
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.map.toString();
   }
}
