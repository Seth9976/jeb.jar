package com.pnfsoftware.jeb.util.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ReusableObjectFactory {
   private Supplier supplier;
   private AtomicInteger suppliedCount = new AtomicInteger();
   private Queue avail = new ConcurrentLinkedQueue();
   private Queue used = new ConcurrentLinkedQueue();

   public ReusableObjectFactory(Supplier var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.supplier = var1;
      }
   }

   public int getSuppliedCount() {
      return this.suppliedCount.get();
   }

   public Object get() {
      Object var1 = this.avail.poll();
      if (var1 != null) {
         this.used.add(var1);
      } else {
         var1 = this.supplier.get();
         if (var1 == null) {
            throw new RuntimeException("The supplier returned a null reference");
         }

         this.suppliedCount.incrementAndGet();
         this.used.add(var1);
      }

      return var1;
   }

   public void release(Object var1) {
      if (!this.used.remove(var1)) {
         throw new IllegalStateException("The object requested for release is not in-use!");
      } else {
         this.avail.add(var1);
      }
   }

   public List list() {
      ArrayList var1 = new ArrayList(this.avail);
      var1.addAll(this.used);
      return var1;
   }
}
