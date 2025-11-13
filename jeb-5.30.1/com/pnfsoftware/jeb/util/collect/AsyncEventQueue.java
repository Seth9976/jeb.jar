package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Ser
public class AsyncEventQueue {
   @SerId(1)
   private ConcurrentLinkedQueue q = new ConcurrentLinkedQueue();
   @SerId(2)
   private int capacity;

   public AsyncEventQueue() {
      this(Integer.MAX_VALUE);
   }

   public AsyncEventQueue(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Capacity must be positive");
      } else {
         this.capacity = var1;
      }
   }

   public boolean add(Object var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.q.size() > this.capacity) {
         return false;
      } else {
         this.q.add(var1);
         return true;
      }
   }

   public Object pull() {
      return this.q.isEmpty() ? null : this.q.remove();
   }

   public List pullAll() {
      ArrayList var1 = new ArrayList(this.q);
      this.q.clear();
      return var1;
   }

   public List readAll() {
      return new ArrayList(this.q);
   }

   public int size() {
      return this.q.size();
   }

   public boolean isEmpty() {
      return this.q.isEmpty();
   }

   public void clear() {
      this.q.clear();
   }

   @Override
   public String toString() {
      return this.q.toString();
   }
}
