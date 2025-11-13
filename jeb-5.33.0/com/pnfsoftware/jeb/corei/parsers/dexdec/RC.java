package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RC {
   private final int pC;
   private AtomicInteger A = new AtomicInteger();
   private Queue kS = new ConcurrentLinkedQueue();
   private Queue wS = new ConcurrentLinkedQueue();

   public RC(int var1) {
      this.pC = var1;
   }

   protected abstract Object A();

   protected abstract void pC(Object var1);

   public Object kS() {
      Object var1 = this.kS.poll();
      if (var1 != null) {
         this.wS.add(var1);
         this.pC();
      } else {
         var1 = this.A();
         if (var1 == null) {
            throw new RuntimeException("The supplier returned a null reference");
         }

         this.A.incrementAndGet();
         this.wS.add(var1);
      }

      return var1;
   }

   public void A(Object var1) {
      if (!this.wS.remove(var1)) {
         throw new IllegalStateException("The object requested for release is not in-use!");
      } else {
         this.kS.add(var1);
         this.pC();
      }
   }

   public List wS() {
      ArrayList var1 = new ArrayList(this.kS.size() + this.wS.size());
      var1.addAll(this.kS);
      var1.addAll(this.wS);
      return var1;
   }

   private void pC() {
      if (this.pC >= 0) {
         while (this.kS.size() >= this.pC) {
            Object var1 = this.kS.poll();
            if (var1 == null) {
               break;
            }

            this.pC(var1);
         }
      }
   }
}
