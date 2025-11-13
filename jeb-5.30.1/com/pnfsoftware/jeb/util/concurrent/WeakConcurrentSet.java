package com.pnfsoftware.jeb.util.concurrent;

import java.util.Iterator;
import java.util.Map.Entry;

public class WeakConcurrentSet implements Iterable, Runnable {
   final WeakConcurrentMap target;

   public WeakConcurrentSet(WeakConcurrentSet.Cleaner var1) {
      switch (var1) {
         case INLINE:
            this.target = new WeakConcurrentMap.WithInlinedExpunction();
            break;
         case THREAD:
         case MANUAL:
            this.target = new WeakConcurrentMap(var1 == WeakConcurrentSet.Cleaner.THREAD);
            break;
         default:
            throw new AssertionError();
      }
   }

   public boolean add(Object var1) {
      return this.target.put(var1, Boolean.TRUE) == null;
   }

   public boolean contains(Object var1) {
      return this.target.containsKey(var1);
   }

   public boolean remove(Object var1) {
      return this.target.remove(var1) != null;
   }

   public void clear() {
      this.target.clear();
   }

   public int approximateSize() {
      return this.target.approximateSize();
   }

   @Override
   public void run() {
      this.target.run();
   }

   public void expungeStaleEntries() {
      this.target.expungeStaleEntries();
   }

   public Thread getCleanerThread() {
      return this.target.getCleanerThread();
   }

   @Override
   public Iterator iterator() {
      return new WeakConcurrentSet.ReducingIterator(this.target.iterator());
   }

   public static enum Cleaner {
      THREAD,
      INLINE,
      MANUAL;
   }

   private static class ReducingIterator implements Iterator {
      private final Iterator iterator;

      private ReducingIterator(Iterator var1) {
         this.iterator = var1;
      }

      @Override
      public void remove() {
         this.iterator.remove();
      }

      @Override
      public Object next() {
         return ((Entry)this.iterator.next()).getKey();
      }

      @Override
      public boolean hasNext() {
         return this.iterator.hasNext();
      }
   }
}
