package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cox;
import java.util.List;

@Ser
public class Nt extends cox {
   @SerId(1)
   iZ q;
   @SerId(2)
   oM RF;

   Nt(iZ var1, oM var2) {
      this.q = var1;
      this.RF = var2;
   }

   Nt(vn var1) {
      this.q(var1);
      this.q = (iZ)var1.xK();
   }

   public iZ q() {
      return this.q;
   }

   public oM RF() {
      return this.RF;
   }

   public int xK() {
      return this.RF.q.size();
   }

   public List Dw() {
      return this.RF.q;
   }

   public int q(int var1) {
      return (Integer)this.RF.q.get(var1);
   }

   @Override
   protected String Uv() {
      return "__f" + this.gO();
   }

   public String oW() {
      return Strings.ff("proto=%s,body=%s", this.q, this.RF.Uv());
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), this.q, this.RF);
   }
}
