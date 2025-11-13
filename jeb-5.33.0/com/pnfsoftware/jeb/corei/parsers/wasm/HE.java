package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class HE extends KD {
   public HE(String var1, String var2, Mh var3) {
      super(var1, var2, var3);
   }

   @Override
   public String toString() {
      return Strings.ff("{imported-table}:%s", super.toString());
   }
}
