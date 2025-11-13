package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DataHints {
   private static final int ADDRESS_MASK = 15;
   public static final int ABSOLUTE_ADDRESS = 1;
   public static final int RELATIVE_ADDRESS = 2;
   public static final int NO_ADDRESS = 3;
   private static final int REFERENCE_MASK = 240;
   public static final int INNER_REFERENCE = 16;
   @SerId(1)
   int addressCalculationHint;

   public void setAddressCalculationHint(int var1) {
      this.addressCalculationHint = this.addressCalculationHint & -16 | var1;
   }

   public int getAddressCalculationHint() {
      return this.addressCalculationHint & 15;
   }

   public void setReferenceHint(int var1) {
      this.addressCalculationHint = this.addressCalculationHint & -241 | var1;
   }

   public int getReferenceHint() {
      return this.addressCalculationHint & 240;
   }
}
