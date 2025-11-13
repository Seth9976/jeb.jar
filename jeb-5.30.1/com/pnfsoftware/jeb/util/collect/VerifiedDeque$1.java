package com.pnfsoftware.jeb.util.collect;

class VerifiedDeque$1 implements VerifiedDeque.FailureHandler {
   VerifiedDeque$1(VerifiedDeque var1) {
      this.this$0 = var1;
   }

   @Override
   public int notInWhitelist(Object var1) {
      return -1;
   }

   @Override
   public int inBlacklist(Object var1) {
      return -1;
   }
}
