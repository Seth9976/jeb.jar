package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class vb {
   private final int q;
   private AtomicInteger RF = new AtomicInteger();
   private Queue xK = new ConcurrentLinkedQueue();
   private Queue Dw = new ConcurrentLinkedQueue();

   public vb() {
      this(-1);
   }

   public vb(int var1) {
      this.q = var1;
   }

   protected abstract Object RF();

   protected abstract void q(Object var1);

   public int xK() {
      return this.RF.get();
   }

   public Object Dw() {
      Object var1 = this.xK.poll();
      if (var1 != null) {
         this.Dw.add(var1);
         this.q();
      } else {
         var1 = this.RF();
         if (var1 == null) {
            throw new RuntimeException("The supplier returned a null reference");
         }

         this.RF.incrementAndGet();
         this.Dw.add(var1);
      }

      return var1;
   }

   public void RF(Object var1) {
      if (!this.Dw.remove(var1)) {
         throw new IllegalStateException("The object requested for release is not in-use!");
      } else {
         this.xK.add(var1);
         this.q();
      }
   }

   public List Uv() {
      ArrayList var1 = new ArrayList(this.xK.size() + this.Dw.size());
      var1.addAll(this.xK);
      var1.addAll(this.Dw);
      return var1;
   }

   private void q() {
      if (this.q >= 0) {
         while (this.xK.size() >= this.q) {
            Object var1 = this.xK.poll();
            if (var1 == null) {
               break;
            }

            this.q(var1);
         }
      }
   }
}
