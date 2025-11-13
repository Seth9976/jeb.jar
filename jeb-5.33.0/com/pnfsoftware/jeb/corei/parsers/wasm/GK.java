package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class GK {
   @SerId(1)
   int pC;
   @SerId(2)
   String A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;

   public GK(int var1, String var2, int var3, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d%s,payload:%Xh(%Xh)", this.pC, this.A == null ? "" : "('" + this.A + "')", this.kS, this.wS);
   }
}
