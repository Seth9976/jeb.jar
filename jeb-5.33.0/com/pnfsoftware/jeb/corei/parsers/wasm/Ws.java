package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Ws {
   @SerId(1)
   String pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;

   Ws(String var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s@%d", this.pC, Tb.A(this.A), this.kS);
   }
}
