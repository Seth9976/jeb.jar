package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class SetMap {
   @SerId(1)
   private Map map;
   @SerId(2)
   private CollectionOrder setOrder;
   @SerId(3)
   private int cachedSize;

   public SetMap() {
      this(CollectionOrder.NONE, CollectionOrder.NONE);
   }

   public SetMap(CollectionOrder var1, CollectionOrder var2) {
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

         if (var2 == null) {
            throw new NullPointerException("Illegal collection type");
         } else {
            this.setOrder = var2;
            this.cachedSize = 0;
         }
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
      Set var3 = (Set)this.map.get(var1);
      if (var3 == null) {
         var3 = this.newColl();
         this.map.put(var1, var3);
      }

      if (var3.add(var2)) {
         this.cachedSize++;
      }

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
      Set var2 = (Set)this.map.get(var1);
      if (var2 != null) {
         return false;
      } else {
         this.map.put(var1, this.newColl());
         return true;
      }
   }

   private Set newColl() {
      switch (this.setOrder) {
         case NONE:
            return new HashSet();
         case INSERTION:
            return new LinkedHashSet();
         case NATURAL:
            return new TreeSet();
         default:
            throw new RuntimeException();
      }
   }

   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public boolean containsValue(Object var1) {
      for (Set var3 : this.map.values()) {
         if (var3.contains(var1)) {
            return true;
         }
      }

      return false;
   }

   public Set get(Object var1) {
      return this.get(var1, false);
   }

   public Set get(Object var1, boolean var2) {
      Set var3 = (Set)this.map.get(var1);
      if (var3 != null) {
         return var3;
      } else {
         return var2 ? Collections.emptySet() : null;
      }
   }

   public Set getSafe(Object var1) {
      return this.get(var1, true);
   }

   public Set remove(Object var1) {
      Set var2 = (Set)this.map.remove(var1);
      if (var2 != null) {
         this.cachedSize = this.cachedSize - var2.size();
      }

      return var2;
   }

   public Object removeValue(Object var1, Object var2) {
      Set var3 = (Set)this.map.get(var1);
      if (var3 == null) {
         return null;
      } else if (!var3.remove(var2)) {
         return null;
      } else {
         this.cachedSize--;
         return var2;
      }
   }

   public int removeMulti(Object var1, Collection var2) {
      Set var3 = (Set)this.map.get(var1);
      if (var2 == null) {
         return 0;
      } else {
         int var4 = 0;

         for (Object var6 : var2) {
            if (var3.remove(var6)) {
               this.cachedSize--;
               var4++;
            }
         }

         return var4;
      }
   }

   public Set values() {
      Set var1 = this.newColl();

      for (Set var3 : this.map.values()) {
         var1.addAll(var3);
      }

      return Collections.unmodifiableSet(var1);
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
