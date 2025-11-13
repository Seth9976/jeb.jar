package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Collection;
import java.util.Deque;

@SerDisabled
public class VerifiedDeque {
   private final VerifiedDeque.FailureHandler defaultHandler = new VerifiedDeque$1(this);
   Deque deque;
   Collection whitelist;
   Collection blacklist;
   VerifiedDeque.FailureHandler failureHandler;

   public static VerifiedDeque wrap(Deque var0, Collection var1, Collection var2, VerifiedDeque.FailureHandler var3) {
      return new VerifiedDeque(var0, var1, var2, var3);
   }

   private VerifiedDeque(Deque var1, Collection var2, Collection var3, VerifiedDeque.FailureHandler var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.deque = var1;
         this.whitelist = var2;
         this.blacklist = var3;
         this.failureHandler = var4 != null ? var4 : this.defaultHandler;
      }
   }

   private boolean check(Object var1) {
      int var2 = 1;
      if (this.whitelist != null && !this.whitelist.contains(var1)) {
         var2 = this.failureHandler.notInWhitelist(var1);
      } else if (this.blacklist != null && this.blacklist.contains(var1)) {
         var2 = this.failureHandler.notInWhitelist(var1);
      }

      if (var2 == 0) {
         return false;
      } else if (var2 < 0) {
         throw new IllegalArgumentException("The element cannot be added to the collection");
      } else {
         return true;
      }
   }

   public boolean push(Object var1) {
      if (this.check(var1)) {
         this.deque.push(var1);
         return true;
      } else {
         return true;
      }
   }

   public interface FailureHandler {
      int notInWhitelist(Object var1);

      int inBlacklist(Object var1);
   }
}
