package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cht;

@Ser
public class Mh extends cht {
   @SerId(1)
   int pC;
   @SerId(2)
   p A;

   Mh(int var1, p var2) {
      this.pC = var1;
      this.A = var2;
   }

   Mh(HE var1) {
      this.pC(var1);
      this.pC = ((Mh)var1.kS()).pC;
      this.A = ((Mh)var1.kS()).A;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), Tb.kS(this.pC), this.A);
   }
}
