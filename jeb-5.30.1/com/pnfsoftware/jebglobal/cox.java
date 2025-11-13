package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class cox {
   @SerId(1)
   private Integer q;
   @SerId(2)
   private boolean RF;
   @SerId(3)
   private String Uv;
   @SerId(4)
   private String oW;
   @SerId(5)
   public long xK;
   @SerId(6)
   public int Dw;

   public void RF(int var1) {
      if (this.q != null) {
         throw new IllegalStateException();
      } else if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public int gO() {
      return this.q == null ? -1 : this.q;
   }

   protected void q(com.pnfsoftware.jeb.corei.parsers.wasm.qV var1) {
      this.RF = true;
      this.Uv = var1.q();
      this.oW = var1.RF();
   }

   public boolean nf() {
      return this.RF;
   }

   public boolean gP() {
      return !this.nf();
   }

   public void q(String var1) {
      if (this.nf()) {
         throw new IllegalStateException();
      } else {
         this.oW = var1;
      }
   }

   public boolean za() {
      return this.gP() && this.oW != null;
   }

   protected String Uv() {
      return "__internal_entry_" + this.q;
   }

   public boolean lm() {
      return this.oW != null;
   }

   public String zz() {
      return this.oW == null ? this.Uv() : this.oW;
   }

   public String JY() {
      if (this.oW == null) {
         return this.zz();
      } else {
         return this.Uv == null ? this.oW : this.Uv + "." + this.oW;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("#%d=%s(%b)", this.gO(), this.JY(), this.gP());
   }
}
