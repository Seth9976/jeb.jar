package com.pnfsoftware.jebglobal;

public class du extends f {
   private Integer q;
   private Integer RF;
   private Gn.CU xK;

   @Override
   final Gn q() {
      String var1 = "";
      if (this.RF == null) {
         var1 = String.valueOf("").concat(" size");
      }

      if (this.xK == null) {
         var1 = String.valueOf(var1).concat(" type");
      }

      if (this.q == null) {
         var1 = String.valueOf(var1).concat(" data");
      }

      if (!var1.isEmpty()) {
         throw new IllegalStateException();
      } else {
         return new Gn.eo(this.RF, this.xK, this.q);
      }
   }

   @Override
   final f q(int var1) {
      this.RF = var1;
      return this;
   }

   @Override
   final f q(Gn.CU var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.xK = var1;
         return this;
      }
   }

   @Override
   final f RF(int var1) {
      this.q = var1;
      return this;
   }
}
