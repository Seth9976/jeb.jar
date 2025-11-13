package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.DeserializerHelper;
import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomRead;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;

@Ser
@SerVersion(2)
public class WeakIdentityHashMap {
   @SerTransient
   private HashMap map;
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
      this.expunge();
      this.rlistForStorage = this.toList();
      var1.saveStandard();
      this.rlistForStorage = null;
   }

   @SerCustomRead
   private void load(DeserializerHelper var1) throws IOException {
      if (var1.getSerializedVersion() == 0) {
         ArrayList var2 = (ArrayList)var1.read();
         ArrayList var3 = (ArrayList)var1.read();
         this.rlistForStorage = new ArrayList(var2.size());

         for (int var4 = 0; var4 < var2.size(); var4++) {
            this.rlistForStorage.add(new Couple(var2.get(var4), var3.get(var4)));
         }

         this.initialCapacity = 16;
         this.loadFactor = 0.75F;
      } else if (var1.getSerializedVersion() == 1) {
         var1.loadStandard();
         this.rlistForStorage = (List)var1.read();
      } else {
         var1.loadStandard();
      }
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

   List toList() {
      this.expunge();
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.map.entrySet()) {
         if (var3.getKey() != null) {
            var1.add(new Couple(((WeakIdentityHashMap.IdentityWeakKeyRef)var3.getKey()).get(), var3.getValue()));
         }
      }

      return var1;
   }

   public WeakIdentityHashMap(int var1, float var2) {
      this.initialCapacity = var1;
      this.loadFactor = var2;
      this.init();
   }

   public WeakIdentityHashMap(int var1) {
      this(var1, 0.75F);
   }

   public WeakIdentityHashMap() {
      this(16);
   }

   public Object get(Object var1) {
      this.expunge();
      return this.map.get(this.makeReference(var1));
   }

   public Set keySet() {
      this.expunge();
      HashSet var1 = new HashSet();

      for (WeakIdentityHashMap.IdentityWeakKeyRef var3 : this.map.keySet()) {
         Object var4 = var3.get();
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   public int size() {
      this.expunge();
      int var1 = 0;

      for (WeakIdentityHashMap.IdentityWeakKeyRef var3 : this.map.keySet()) {
         Object var4 = var3.get();
         if (var4 != null) {
            var1++;
         }
      }

      return var1;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Collection values() {
      this.expunge();
      return this.map.values();
   }

   public Set entrySet() {
      this.expunge();
      HashSet var1 = new HashSet();

      for (WeakIdentityHashMap.IdentityWeakKeyRef var3 : this.map.keySet()) {
         Object var4 = var3.get();
         if (var4 != null) {
            var1.add(new DefaultMapEntry(var4, this.map.get(var3)));
         }
      }

      return var1;
   }

   public Object put(Object var1, Object var2) {
      this.expunge();
      if (var1 == null) {
         throw new IllegalArgumentException("Null key");
      } else {
         return this.putUnsafe(var1, var2);
      }
   }

   public Object putUnsafe(Object var1, Object var2) {
      return this.map.put(this.makeReference(var1, this.queue), var2);
   }

   public Object remove(Object var1) {
      this.expunge();
      return this.map.remove(this.makeReference(var1));
   }

   public void clear() {
      this.expunge();
      this.map.clear();
   }

   @Override
   public String toString() {
      return this.map.toString();
   }

   private void expunge() {
      Reference var1;
      while ((var1 = this.queue.poll()) != null) {
         this.map.remove(var1);
      }
   }

   private WeakIdentityHashMap.IdentityWeakKeyRef makeReference(Object var1) {
      return new WeakIdentityHashMap.IdentityWeakKeyRef(var1);
   }

   private WeakIdentityHashMap.IdentityWeakKeyRef makeReference(Object var1, ReferenceQueue var2) {
      return new WeakIdentityHashMap.IdentityWeakKeyRef(var1, var2);
   }

   @SerDisabled
   private static class IdentityWeakKeyRef extends WeakReference {
      private final int hashCode;

      IdentityWeakKeyRef(Object var1) {
         this(var1, null);
      }

      IdentityWeakKeyRef(Object var1, ReferenceQueue var2) {
         super(var1, var2);
         this.hashCode = var1 == null ? 0 : System.identityHashCode(var1);
      }

      @Override
      public int hashCode() {
         return this.hashCode;
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof WeakIdentityHashMap.IdentityWeakKeyRef var2)) {
            return false;
         } else {
            Object var3 = this.get();
            return var3 != null && var3 == var2.get();
         }
      }

      @Override
      public String toString() {
         Object var1 = this.get();
         return var1 == null ? null : var1.toString();
      }
   }
}
