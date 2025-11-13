package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class tl {
   @SerId(1)
   int q;
   @SerId(2)
   Integer RF;
   @SerId(3)
   boolean xK;

   tl(int var1, Integer var2, boolean var3) {
      if (var3 && var2 == null) {
         throw new RuntimeException("Illegal limits: shared linear memory requires a max value");
      } else {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d%s%s", this.q, this.RF == null ? "" : "(max:" + this.RF + ")", this.xK ? "[shared]" : "");
   }
}
