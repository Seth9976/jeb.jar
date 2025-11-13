package com.pnfsoftware.jebglobal;

import java.util.function.Predicate;

public class ag {
   Je q;
   Predicate RF;

   public ag(Je var1, Predicate var2) {
      this.q = var1;
      this.RF = var2;
   }

   public boolean q(byte[] var1) {
      return this.RF.test(var1);
   }

   public Je q() {
      return this.q;
   }

   public static Predicate q(rR var0, rR var1) {
      return var2 -> var0.q(var2) == var1.q(var2);
   }

   public static Predicate q(rR var0, rR var1, rR var2) {
      return var3 -> {
         int var4 = var0.q(var3);
         return var4 == var1.q(var3) && var4 == var2.q(var3);
      };
   }
}
