package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cht;

@Ser
public class oP extends cht {
   @SerId(1)
   p pC;

   oP(p var1) {
      this.pC = var1;
   }

   oP(sy var1) {
      this.pC(var1);
      this.pC = ((oP)var1.kS()).pC;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", super.toString(), this.pC);
   }
}
