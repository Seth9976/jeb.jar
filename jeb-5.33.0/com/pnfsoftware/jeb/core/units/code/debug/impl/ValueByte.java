package com.pnfsoftware.jeb.core.units.code.debug.impl;

public class ValueByte extends AbstractValueNumber {
   private byte v;

   public ValueByte(byte var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "byte";
   }

   public Byte getValue() {
      return this.v;
   }
}
