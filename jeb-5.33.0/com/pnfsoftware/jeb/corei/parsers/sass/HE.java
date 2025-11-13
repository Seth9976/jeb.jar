package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class HE {
   @SerId(1)
   AddressSegmentMap pC = new AddressSegmentMap(64);

   public HE.Av pC(String var1, long var2, long var4) {
      return (HE.Av)this.pC.add(new HE.Av(var1, var2, var4));
   }

   @Ser
   public static class Av extends LongSegment {
      @SerId(1)
      String pC;

      public Av(String var1, long var2, long var4) {
         super(var2, var4);
         this.pC = var1;
      }
   }
}
