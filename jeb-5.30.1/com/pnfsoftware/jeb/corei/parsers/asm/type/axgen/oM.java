package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class oM {
   @SerId(1)
   String q;
   @SerId(2)
   String RF;
   @SerId(3)
   String xK;
   @SerId(4)
   long Dw;

   oM(String var1, String var2, String var3) {
      if (var1 != null && !var1.isBlank()) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = System.currentTimeMillis() / 1000L;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public String q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public String xK() {
      return this.xK;
   }

   public long Dw() {
      return this.Dw;
   }
}
