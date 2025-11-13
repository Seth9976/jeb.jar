package com.pnfsoftware.jeb.util.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class WeakConcurrentMap extends ReferenceQueue implements Iterable, Runnable {
   private static final AtomicLong ID = new AtomicLong();
   public final ConcurrentMap target = new ConcurrentHashMap();
   private final Thread thread;

   public WeakConcurrentMap(boolean var1) {
      if (var1) {
         this.thread = new Thread(this);
         this.thread.setName("weak-ref-cleaner-" + ID.getAndIncrement());
         this.thread.setPriority(1);
         this.thread.setDaemon(true);
         this.thread.start();
      } else {
         this.thread = null;
      }
   }

   public Object get(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         Object var2 = this.target.get(new WeakConcurrentMap.LatentKey(var1));
         if (var2 == null) {
            var2 = this.defaultValue(var1);
            if (var2 != null) {
               Object var3 = this.target.putIfAbsent(new WeakConcurrentMap.WeakKey(var1, this), var2);
               if (var3 != null) {
                  var2 = var3;
               }
            }
         }

         return var2;
      }
   }

   public boolean containsKey(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.target.containsKey(new WeakConcurrentMap.LatentKey(var1));
      }
   }

   public Object put(Object var1, Object var2) {
      if (var1 != null && var2 != null) {
         return this.target.put(new WeakConcurrentMap.WeakKey(var1, this), var2);
      } else {
         throw new NullPointerException();
      }
   }

   public Object remove(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.target.remove(new WeakConcurrentMap.LatentKey(var1));
      }
   }

   public void clear() {
      this.target.clear();
   }

   protected Object defaultValue(Object var1) {
      return null;
   }

   public Thread getCleanerThread() {
      return this.thread;
   }

   public void expungeStaleEntries() {
      Reference var1;
      while ((var1 = this.poll()) != null) {
         this.target.remove(var1);
      }
   }

   public int approximateSize() {
      return this.target.size();
   }

   @Override
   public void run() {
      try {
         while (true) {
            this.target.remove(this.remove());
         }
      } catch (InterruptedException var1) {
         this.clear();
      }
   }

   @Override
   public Iterator iterator() {
      return new WeakConcurrentMap.EntryIterator(this.target.entrySet().iterator());
   }

   private class EntryIterator implements Iterator {
      private final Iterator iterator;
      private Entry nextEntry;
      private Object nextKey;

      private EntryIterator(Iterator var2) {
         this.iterator = var2;
         this.findNext();
      }

      private void findNext() {
         while (this.iterator.hasNext()) {
            this.nextEntry = (Entry)this.iterator.next();
            this.nextKey = ((WeakConcurrentMap.IWeakKey)this.nextEntry.getKey()).get();
            if (this.nextKey != null) {
               return;
            }
         }

         this.nextEntry = null;
         this.nextKey = null;
      }

      @Override
      public boolean hasNext() {
         return this.nextKey != null;
      }

      public Entry next() {
         if (this.nextKey == null) {
            throw new NoSuchElementException();
         } else {
            WeakConcurrentMap.SimpleEntry var1;
            try {
               var1 = WeakConcurrentMap.this.new SimpleEntry(this.nextKey, this.nextEntry);
            } finally {
               this.findNext();
            }

            return var1;
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private interface IWeakKey {
      Object get();
   }

   private static class LatentKey implements WeakConcurrentMap.IWeakKey {
      final Object key;
      private final int hashCode;

      LatentKey(Object var1) {
         this.key = var1;
         this.hashCode = System.identityHashCode(var1);
      }

      @Override
      public boolean equals(Object var1) {
         return var1 instanceof WeakConcurrentMap.LatentKey
            ? ((WeakConcurrentMap.LatentKey)var1).key == this.key
            : ((WeakConcurrentMap.WeakKey)var1).get() == this.key;
      }

      @Override
      public int hashCode() {
         return this.hashCode;
      }

      @Override
      public Object get() {
         return this.key;
      }
   }

   private class SimpleEntry implements Entry {
      private final Object key;
      final Entry entry;

      private SimpleEntry(Object var2, Entry var3) {
         this.key = var2;
         this.entry = var3;
      }

      @Override
      public Object getKey() {
         return this.key;
      }

      @Override
      public Object getValue() {
         return this.entry.getValue();
      }

      @Override
      public Object setValue(Object var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            return this.entry.setValue(var1);
         }
      }
   }

   private static class WeakKey extends WeakReference implements WeakConcurrentMap.IWeakKey {
      private final int hashCode;

      WeakKey(Object var1, ReferenceQueue var2) {
         super(var1, var2);
         this.hashCode = System.identityHashCode(var1);
      }

      @Override
      public int hashCode() {
         return this.hashCode;
      }

      @Override
      public boolean equals(Object var1) {
         return var1 instanceof WeakConcurrentMap.LatentKey
            ? ((WeakConcurrentMap.LatentKey)var1).key == this.get()
            : ((WeakConcurrentMap.WeakKey)var1).get() == this.get();
      }
   }

   public static class WithInlinedExpunction extends WeakConcurrentMap {
      public WithInlinedExpunction() {
         super(false);
      }

      @Override
      public Object get(Object var1) {
         this.expungeStaleEntries();
         return super.get(var1);
      }

      @Override
      public boolean containsKey(Object var1) {
         this.expungeStaleEntries();
         return super.containsKey(var1);
      }

      @Override
      public Object put(Object var1, Object var2) {
         this.expungeStaleEntries();
         return super.put(var1, var2);
      }

      @Override
      public Object remove(Object var1) {
         this.expungeStaleEntries();
         return super.remove(var1);
      }

      @Override
      public Iterator iterator() {
         this.expungeStaleEntries();
         return super.iterator();
      }

      @Override
      public int approximateSize() {
         this.expungeStaleEntries();
         return super.approximateSize();
      }
   }
}
