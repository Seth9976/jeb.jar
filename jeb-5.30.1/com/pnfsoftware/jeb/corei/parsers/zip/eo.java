package com.pnfsoftware.jeb.corei.parsers.zip;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class eo {
   @SerId(1)
   String q;
   @SerId(2)
   int RF;
   @SerId(3)
   Map xK;
   @SerId(4)
   IUnit Dw;

   public eo(String var1) {
      this.q = var1;
      this.xK = new HashMap();
   }

   public eo(String var1, int var2, IUnit var3) {
      this.q = var1;
      this.RF = var2;
      this.Dw = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("[n:\"%s\",s:%d,c:%s]", this.q, this.RF, this.xK);
   }
}
