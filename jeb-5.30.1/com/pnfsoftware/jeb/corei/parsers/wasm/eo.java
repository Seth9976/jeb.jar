package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class eo {
   @SerId(1)
   List q;
   @SerId(2)
   int RF;

   eo(List var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("targets=%s,default=%d", this.q, this.RF);
   }
}
