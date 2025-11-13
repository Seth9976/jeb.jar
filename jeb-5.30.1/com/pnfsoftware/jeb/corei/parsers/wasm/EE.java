package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class EE {
   @SerId(1)
   int q;
   @SerId(2)
   boolean RF;

   EE(int var1, boolean var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String toString() {
      return Xa.xK(this.q) + (this.RF ? "" : "{immutable}");
   }
}
