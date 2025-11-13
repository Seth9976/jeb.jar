package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;

@Ser
public class WeakValueMap implements Map {
   @SerTransient
   private Map map;
   @SerTransient
   private ReferenceQueue queue;
   @SerId(1)
   private int initialCapacity;
   @SerId(2)
   private float loadFactor;
   @SerId(3)
   private List rlistForStorage;

   @SerCustomWrite
   private void save(SerializerHelper var1) throws IOException {
      this.rlistForStorage = this.toList();
      var1.saveStandard();
      this.rlistForStorage = null;
   }

   @SerCustomInitPostGraph
   private void postInit() {
      for (Couple var2 : this.rlistForStorage) {
         this.put(var2.getFirst(), var2.getSecond());
      }

      this.rlistForStorage = null;
   }

   @SerCustomInit
   private void init() {
      this.queue = new ReferenceQueue();
      this.map = new HashMap(this.initialCapacity, this.loadFactor);
   }

   public WeakValueMap(int var1, float var2) {
      this.initialCapacity = var1;
      this.loadFactor = var2;
      this.init();
   }

   public WeakValueMap(int var1) {
      this(var1, 0.75F);
   }

   public WeakValueMap() {
      this(16);
   }

   public WeakValueMap(Map var1) {
      this(Math.max(2 * var1.size(), 11));
      this.putAll(var1);
   }

   @Override
   public void clear() {
      this.processQueue();
      this.map.clear();
   }

   @Override
   public boolean containsKey(Object var1) {
      this.processQueue();
      return this.map.containsKey(var1);
   }

   @Override
   public boolean containsValue(Object var1) {
      this.processQueue();

      for (Entry var3 : this.map.entrySet()) {
         if (var3.getValue() != null) {
            Object var4 = ((WeakValueMap.WeakValueRef)var3.getValue()).get();
            if (var1 == null ? var4 == null : var1.equals(var4)) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public Set entrySet() {
      this.processQueue();
      HashSet var1 = new HashSet();

      for (Entry var3 : this.map.entrySet()) {
         if (var3.getValue() != null) {
            var1.add(new DefaultMapEntry(var3.getKey(), ((WeakValueMap.WeakValueRef)var3.getValue()).get()));
         }
      }

      return var1;
   }

   List toList() {
      this.processQueue();
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.map.entrySet()) {
         if (var3.getValue() != null) {
            var1.add(new Couple(var3.getKey(), ((WeakValueMap.WeakValueRef)var3.getValue()).get()));
         }
      }

      return var1;
   }

   @Override
   public Object get(Object var1) {
      this.processQueue();
      WeakReference var2 = (WeakReference)this.map.get(var1);
      return var2 == null ? null : var2.get();
   }

   @Override
   public boolean isEmpty() {
      this.processQueue();
      return this.map.isEmpty();
   }

   @Override
   public Set keySet() {
      this.processQueue();
      return this.map.keySet();
   }

   @Override
   public Object put(Object var1, Object var2) {
      this.processQueue();
      WeakReference var3 = (WeakReference)this.map.put(var1, WeakValueMap.WeakValueRef.create(var1, var2, this.queue));
      return var3 == null ? null : var3.get();
   }

   @Override
   public void putAll(Map var1) {
      this.processQueue();

      for (Entry var3 : var1.entrySet()) {
         this.put(var3.getKey(), var3.getValue());
      }
   }

   @Override
   public Object remove(Object var1) {
      this.processQueue();
      WeakReference var2 = (WeakReference)this.map.remove(var1);
      return var2 == null ? null : var2.get();
   }

   @Override
   public int size() {
      this.processQueue();
      return this.map.size();
   }

   @Override
   public Collection values() {
      this.processQueue();
      ArrayList var1 = new ArrayList();

      for (WeakReference var3 : this.map.values()) {
         if (var3 != null) {
            var1.add(var3.get());
         }
      }

      return var1;
   }

   private void processQueue() {
      WeakValueMap.WeakValueRef var1;
      while ((var1 = (WeakValueMap.WeakValueRef)this.queue.poll()) != null) {
         if (var1 == this.map.get(var1.key)) {
            this.map.remove(var1.key);
         }
      }
   }

   @Override
   public int hashCode() {
      return this.map.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.map.equals(var1);
   }

   @Override
   public String toString() {
      return this.map.toString();
   }

   @SerDisabled
   private static class WeakValueRef extends WeakReference {
      public Object key;

      private WeakValueRef(Object var1, Object var2, ReferenceQueue var3) {
         super(var2, var3);
         this.key = var1;
      }

      private static WeakValueMap.WeakValueRef create(Object var0, Object var1, ReferenceQueue var2) {
         return var1 == null ? null : new WeakValueMap.WeakValueRef(var0, var1, var2);
      }

      @Override
      public int hashCode() {
         Object var1 = this.get();
         return var1 == null ? 0 : var1.hashCode();
      }

      @Override
      public boolean equals(Object var1) {
         Object var2 = this.get();
         return var2 == null ? var1 == null : var2.equals(var1);
      }

      @Override
      public String toString() {
         Object var1 = this.get();
         return var1 == null ? null : var1.toString();
      }
   }
}
