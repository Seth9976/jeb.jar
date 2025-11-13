package com.pnfsoftware.jeb.core.units.code.debug.impl;

public class ValueShort extends AbstractValueNumber {
   private short v;

   public ValueShort(short var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "short";
   }

   public Short getValue() {
      return this.v;
   }
}
