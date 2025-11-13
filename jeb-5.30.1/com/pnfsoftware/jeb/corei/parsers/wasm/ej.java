package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ej {
   @SerId(1)
   String q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;

   ej(String var1, int var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s@%d", this.q, Xa.RF(this.RF), this.xK);
   }
}
