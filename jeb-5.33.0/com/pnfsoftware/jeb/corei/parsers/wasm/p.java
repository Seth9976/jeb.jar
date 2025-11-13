package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class p {
   @SerId(1)
   int pC;
   @SerId(2)
   Integer A;
   @SerId(3)
   boolean kS;

   p(int var1, Integer var2, boolean var3) {
      if (var3 && var2 == null) {
         throw new RuntimeException("Illegal limits: shared linear memory requires a max value");
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d%s%s", this.pC, this.A == null ? "" : "(max:" + this.A + ")", this.kS ? "[shared]" : "");
   }
}
