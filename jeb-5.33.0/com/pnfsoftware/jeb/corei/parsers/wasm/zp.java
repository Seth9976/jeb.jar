package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class zp {
   @SerId(1)
   int pC;
   @SerId(2)
   boolean A;

   zp(int var1, boolean var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Tb.kS(this.pC) + (this.A ? "" : "{immutable}");
   }
}
