package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Ws {
   @SerId(1)
   String pC;
   @SerId(2)
   String A;
   @SerId(3)
   String kS;
   @SerId(4)
   long wS;

   Ws(String var1, String var2, String var3) {
      if (var1 != null && !var1.isBlank()) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = System.currentTimeMillis() / 1000L;
      } else {
         throw new IllegalArgumentException();
      }
   }
}
