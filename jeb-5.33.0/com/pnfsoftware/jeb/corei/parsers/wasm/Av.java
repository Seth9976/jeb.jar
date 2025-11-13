package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class Av {
   @SerId(1)
   List pC;
   @SerId(2)
   int A;

   Av(List var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("targets=%s,default=%d", this.pC, this.A);
   }
}
