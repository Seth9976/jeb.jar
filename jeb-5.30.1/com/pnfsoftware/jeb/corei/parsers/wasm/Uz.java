package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cox;

@Ser
public class Uz extends cox {
   @SerId(1)
   int q;
   @SerId(2)
   tl RF;

   Uz(int var1, tl var2) {
      this.q = var1;
      this.RF = var2;
   }

   Uz(oL var1) {
      this.q(var1);
      this.q = ((Uz)var1.xK()).q;
      this.RF = ((Uz)var1.xK()).RF;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%s", super.toString(), Xa.xK(this.q), this.RF);
   }
}
