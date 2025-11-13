package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Strings;

public class ValueFloat extends AbstractValueNumber {
   private float v;

   public ValueFloat(float var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "float";
   }

   public Float getValue() {
      return this.v;
   }

   @Override
   public String toString() {
      return Strings.ff("%f", this.getValue());
   }
}
