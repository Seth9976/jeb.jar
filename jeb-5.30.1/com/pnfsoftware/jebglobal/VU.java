package com.pnfsoftware.jebglobal;

import java.util.Map;

public class VU extends Dg {
   private Integer q;
   private Integer RF;
   private Integer xK;
   private PB Dw;
   private Integer Uv;
   private Gn oW;
   private Map gO;

   @Override
   final PB.eo q() {
      return new PB.eo.eo(this.RF, this.q, this.xK, this.oW, this.gO, this.Uv, this.Dw);
   }

   @Override
   final Dg q(int var1) {
      this.RF = var1;
      return this;
   }

   @Override
   final Dg q(Gn var1) {
      this.oW = var1;
      return this;
   }

   @Override
   final Dg q(PB var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.Dw = var1;
         return this;
      }
   }

   @Override
   final Dg q(Map var1) {
      this.gO = var1;
      return this;
   }

   @Override
   final Dg RF(int var1) {
      this.q = var1;
      return this;
   }

   @Override
   final Dg xK(int var1) {
      this.xK = var1;
      return this;
   }

   @Override
   final Dg Dw(int var1) {
      this.Uv = var1;
      return this;
   }
}
