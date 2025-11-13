package com.pnfsoftware.jebglobal;

public class aad {
   long q;
   Object RF;

   public aad(long var1, Object var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
         this.RF = var3;
      }
   }

   public long q() {
      return this.q;
   }

   public Object RF() {
      return this.RF;
   }
}
