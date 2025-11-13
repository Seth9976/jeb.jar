package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Formatter;

public class ValueRaw extends AbstractValuePrimitive {
   byte[] bytes;

   public ValueRaw(byte[] var1) {
      if (var1 == null) {
         throw new NullPointerException("ValueRaw cannot be null");
      } else {
         this.bytes = var1;
      }
   }

   @Override
   public String getTypeName() {
      return "raw";
   }

   public byte[] getValue() {
      return this.bytes;
   }

   @Override
   public String toString() {
      return Formatter.formatBinaryLine(this.bytes).toString();
   }
}
