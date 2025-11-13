package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class K {
   @SerId(1)
   int pC;
   @SerId(2)
   qt A;
   @SerId(3)
   List kS;

   K(int var1, qt var2, List var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("target:%s,functions:%s", this.A, this.kS);
   }
}
