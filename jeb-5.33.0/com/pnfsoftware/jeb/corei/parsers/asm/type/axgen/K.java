package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;

@Ser
public class K {
   @SerId(1)
   Ws pC;
   @SerId(2)
   Map A;

   public K(Ws var1, Map var2) {
      this.pC = var1;
      this.A = var2;
   }

   public int pC() {
      return this.A.size();
   }

   public Av pC(String var1) {
      if (var1 == null) {
         return null;
      } else {
         if (var1.startsWith("→")) {
            var1 = var1.substring("→".length());
         }

         return (Av)this.A.get(var1);
      }
   }
}
