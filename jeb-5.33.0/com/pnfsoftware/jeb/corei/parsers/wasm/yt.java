package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class yt extends KD {
   public yt(String var1, String var2, DH var3) {
      super(var1, var2, var3);
   }

   @Override
   public String toString() {
      return Strings.ff("{imported-function}:%s", super.toString());
   }
}
