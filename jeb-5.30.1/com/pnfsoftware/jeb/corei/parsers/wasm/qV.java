package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class qV {
   @SerId(1)
   String q;
   @SerId(2)
   String RF;
   @SerId(3)
   Object xK;

   public qV(String var1, String var2, Object var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public String q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public Object xK() {
      return this.xK;
   }

   public String Dw() {
      return Strings.ff("%s.%s", this.q, this.RF);
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", this.Dw(), this.xK());
   }
}
