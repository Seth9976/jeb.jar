package com.pnfsoftware.jeb.util.base;

class ExecutionTargetWithProgressCallback$1 implements IProgressCallback {
   ExecutionTargetWithProgressCallback$1(ExecutionTargetWithProgressCallback var1) {
      this.this$0 = var1;
   }

   @Override
   public void setCurrent(long var1) {
      if (this.this$0.proxy != null) {
         this.this$0.proxy.setCurrent(var1);
      }
   }

   @Override
   public long getCurrent() {
      return this.this$0.proxy != null ? this.this$0.proxy.getCurrent() : 0L;
   }

   @Override
   public void setTotal(long var1) {
      if (this.this$0.proxy != null) {
         this.this$0.proxy.setTotal(var1);
      }
   }

   @Override
   public void updateTotal(long var1) {
      if (this.this$0.proxy != null) {
         this.this$0.proxy.updateTotal(var1);
      }
   }

   @Override
   public long getTotal() {
      return this.this$0.proxy != null ? this.this$0.proxy.getTotal() : 0L;
   }

   @Override
   public long increment() {
      return this.this$0.proxy != null ? this.this$0.proxy.increment() : 0L;
   }

   @Override
   public void message(int var1, String var2) {
      if (this.this$0.proxy != null) {
         this.this$0.proxy.message(var1, var2);
      }
   }
}
