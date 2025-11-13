package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DataHints {
   public static final int ABSOLUTE_ADDRESS = 1;
   public static final int RELATIVE_ADDRESS = 2;
   public static final int NO_ADDRESS = 3;
   @SerId(1)
   int addressCalculationHint;

   public void setAddressCalculationHint(int var1) {
      this.addressCalculationHint = var1;
   }

   public int getAddressCalculationHint() {
      return this.addressCalculationHint;
   }
}
