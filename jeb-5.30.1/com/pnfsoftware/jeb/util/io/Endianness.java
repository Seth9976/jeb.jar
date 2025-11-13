package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.nio.ByteOrder;

@Ser
public enum Endianness {
   BIG_ENDIAN("BIG_ENDIAN"),
   LITTLE_ENDIAN("LITTLE_ENDIAN"),
   MIDDLE_ENDIAN("MIDDLE_ENDIAN");

   private final String name;

   private Endianness(String var3) {
      this.name = var3;
   }

   public ByteOrder toByteOrder() {
      if (this == BIG_ENDIAN) {
         return ByteOrder.BIG_ENDIAN;
      } else if (this == LITTLE_ENDIAN) {
         return ByteOrder.LITTLE_ENDIAN;
      } else {
         throw new UnsupportedOperationException("Cannot convert " + this + " to Java's ByteOrder");
      }
   }

   public boolean isBig() {
      return this == BIG_ENDIAN;
   }

   public boolean isLittle() {
      return this == LITTLE_ENDIAN;
   }

   public boolean isMiddle() {
      return this == MIDDLE_ENDIAN;
   }

   public static Endianness fromByteOrder(ByteOrder var0) {
      if (var0 == ByteOrder.BIG_ENDIAN) {
         return BIG_ENDIAN;
      } else {
         return var0 == ByteOrder.LITTLE_ENDIAN ? LITTLE_ENDIAN : null;
      }
   }

   public static Endianness nativeOrder() {
      return fromByteOrder(ByteOrder.nativeOrder());
   }

   @Override
   public String toString() {
      return this.name;
   }
}
