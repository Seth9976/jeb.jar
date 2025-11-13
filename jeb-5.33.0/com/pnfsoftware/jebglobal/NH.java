package com.pnfsoftware.jebglobal;

import java.util.function.Predicate;

public class NH {
   tz pC;
   Predicate A;

   public NH(tz var1, Predicate var2) {
      this.pC = var1;
      this.A = var2;
   }

   public boolean pC(byte[] var1) {
      return this.A.test(var1);
   }

   public tz pC() {
      return this.pC;
   }

   public static Predicate pC(gZ var0, gZ var1) {
      return var2 -> var0.pC(var2) == var1.pC(var2);
   }

   public static Predicate pC(gZ var0, int var1) {
      return var2 -> var0.pC(var2) == var1;
   }

   public static Predicate pC(gZ var0, gZ var1, gZ var2) {
      return var3 -> {
         int var4 = var0.pC(var3);
         return var4 == var1.pC(var3) && var4 == var2.pC(var3);
      };
   }
}
