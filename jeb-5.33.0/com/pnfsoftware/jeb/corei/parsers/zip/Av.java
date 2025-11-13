package com.pnfsoftware.jeb.corei.parsers.zip;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;

@Ser
public class Av {
   @SerId(1)
   String pC;
   @SerId(2)
   int A;
   @SerId(3)
   Map kS;
   @SerId(4)
   IUnit wS;

   @Override
   public String toString() {
      return Strings.ff("[n:\"%s\",s:%d,c:%s]", this.pC, this.A, this.kS);
   }
}
