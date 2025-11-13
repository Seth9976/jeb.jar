package com.pnfsoftware.jebglobal;

public class Qb {
   long pC;
   Object A;

   public Qb(long var1, Object var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.A = var3;
      }
   }

   public long pC() {
      return this.pC;
   }

   public Object A() {
      return this.A;
   }
}
