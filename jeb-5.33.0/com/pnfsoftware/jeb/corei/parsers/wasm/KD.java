package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class KD {
   @SerId(1)
   String pC;
   @SerId(2)
   String A;
   @SerId(3)
   Object kS;

   public KD(String var1, String var2, Object var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public String pC() {
      return this.pC;
   }

   public String A() {
      return this.A;
   }

   public Object kS() {
      return this.kS;
   }

   public String wS() {
      return Strings.ff("%s.%s", this.pC, this.A);
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", this.wS(), this.kS());
   }
}
