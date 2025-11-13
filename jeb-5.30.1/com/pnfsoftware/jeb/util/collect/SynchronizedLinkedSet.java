package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Ser
public class SynchronizedLinkedSet {
   @SerId(1)
   private LinkedHashSet set0 = new LinkedHashSet();
   @SerTransient
   private Set set;

   @SerCustomInitPostGraph
   private void init() {
      this.set = Collections.synchronizedSet(this.set0);
   }

   public SynchronizedLinkedSet() {
      this.init();
   }

   public int size() {
      return this.set.size();
   }

   public void clear() {
      this.set.clear();
   }

   public boolean isEmpty() {
      return this.set.isEmpty();
   }

   public boolean contains(Object var1) {
      return this.set.contains(var1);
   }

   public boolean add(Object var1) {
      if (var1 == null) {
         throw new NullPointerException("Illegal null key");
      } else {
         return this.set.add(var1);
      }
   }

   public boolean remove(Object var1) {
      return this.set.remove(var1);
   }

   public Set copyOfEntries() {
      synchronized (this.set) {
         return new LinkedHashSet(this.set);
      }
   }

   public Object firstEntry() {
      synchronized (this.set) {
         return this.set.iterator().next();
      }
   }

   @Override
   public String toString() {
      return this.set.toString();
   }
}
