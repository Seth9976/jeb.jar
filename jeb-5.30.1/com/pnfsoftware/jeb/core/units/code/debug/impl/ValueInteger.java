package com.pnfsoftware.jeb.core.units.code.debug.impl;

public class ValueInteger extends AbstractValueNumber {
   private int v;

   public ValueInteger(int var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "int";
   }

   public Integer getValue() {
      return this.v;
   }
}
