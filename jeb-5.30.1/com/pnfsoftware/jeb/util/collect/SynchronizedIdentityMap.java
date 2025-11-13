package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

@Ser
public class SynchronizedIdentityMap {
   @SerId(1)
   private IdentityHashMap map0 = new IdentityHashMap();
   @SerTransient
   private Map map;

   @SerCustomInitPostGraph
   private void init() {
      this.map = Collections.synchronizedMap(this.map0);
   }

   public SynchronizedIdentityMap() {
      this.init();
   }

   public int size() {
      return this.map.size();
   }

   public void clear() {
      this.map.clear();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public boolean containsValue(Object var1) {
      return this.map.containsValue(var1);
   }

   public Object get(Object var1) {
      return this.map.get(var1);
   }

   public Object put(Object var1, Object var2) {
      if (var1 == null) {
         throw new NullPointerException("Illegal null key");
      } else if (var2 == null) {
         throw new NullPointerException("Illegal null value");
      } else {
         return this.map.put(var1, var2);
      }
   }

   public Object remove(Object var1) {
      return this.map.remove(var1);
   }

   public Set copyOfKeys() {
      synchronized (this.map) {
         return new HashSet(this.map.keySet());
      }
   }

   public Object firstKey() {
      synchronized (this.map) {
         return this.map.keySet().iterator().next();
      }
   }

   @Override
   public String toString() {
      return this.map.toString();
   }
}
