package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;

public class OperationRequest {
   private Operation op;
   private long t0;
   private long t1;
   private Boolean result;
   private WeakIdentityHashMap processors;

   public OperationRequest(Operation var1) {
      if (var1 == null) {
         throw new NullPointerException("Null operation");
      } else {
         this.op = var1;
         this.t0 = System.nanoTime();
      }
   }

   public Operation getOperation() {
      return this.op;
   }

   public boolean prepare(IOperable var1) {
      if (this.processors == null) {
         this.processors = new WeakIdentityHashMap();
      }

      if (this.processors.get(var1) != null) {
         return false;
      } else {
         this.processors.put(var1, System.nanoTime());
         return true;
      }
   }

   public void setResult(boolean var1) {
      this.result = var1;
      this.t1 = System.nanoTime();
   }

   public boolean success() {
      return this.result != null && this.result;
   }

   public boolean done() {
      return this.result != null;
   }

   public boolean proceed() {
      return this.result == null;
   }

   public long duration() {
      return this.result == null ? (System.nanoTime() - this.t0) / 1000000L : (this.t1 - this.t0) / 1000000L;
   }
}
