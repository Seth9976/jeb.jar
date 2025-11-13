package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class boj {
   @SerId(1)
   private boi q;

   public boj() {
      this.q = boi.q;
   }

   public boj(boi var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public boi q() {
      return this.q;
   }
}
