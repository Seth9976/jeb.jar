package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class qt {
   @SerId(1)
   String pC;
   @SerId(2)
   String A;
   @SerId(3)
   boolean kS;

   public qt(String var1, String var2, boolean var3) {
      Assert.a(!Strings.isBlank(var1));
      Assert.a(!Strings.isBlank(var2));
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return this.kS ? "(" + this.A + ")" : this.A;
   }
}
