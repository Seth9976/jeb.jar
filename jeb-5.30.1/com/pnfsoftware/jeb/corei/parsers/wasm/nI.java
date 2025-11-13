package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class nI {
   @SerId(1)
   int q;
   @SerId(2)
   Vj RF;
   @SerId(3)
   List xK;

   nI(int var1, Vj var2, List var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("target:%s,functions:%s", this.RF, this.xK);
   }
}
