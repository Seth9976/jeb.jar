package com.pnfsoftware.jeb.core.units.code.debug.impl;

public class ValueLong extends AbstractValueNumber {
   private long v;

   public ValueLong(long var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "long";
   }

   public Long getValue() {
      return this.v;
   }
}
