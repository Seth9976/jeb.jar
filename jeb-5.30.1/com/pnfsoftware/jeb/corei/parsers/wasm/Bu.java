package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cox;

@Ser
public class Bu extends cox {
   @SerId(1)
   tl q;

   Bu(tl var1) {
      this.q = var1;
   }

   Bu(vb var1) {
      this.q(var1);
      this.q = ((Bu)var1.xK()).q;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", super.toString(), this.q);
   }
}
