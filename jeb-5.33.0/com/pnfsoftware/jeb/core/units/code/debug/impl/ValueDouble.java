package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Strings;

public class ValueDouble extends AbstractValueNumber {
   private double v;

   public ValueDouble(double var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "double";
   }

   public Double getValue() {
      return this.v;
   }

   @Override
   public String toString() {
      return Strings.ff("%f", this.getValue());
   }
}
