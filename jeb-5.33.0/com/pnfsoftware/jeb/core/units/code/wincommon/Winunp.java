package com.pnfsoftware.jeb.core.units.code.wincommon;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class Winunp {
   public static final int getSINT8(ByteBuffer var0) {
      return var0.get();
   }

   public static final int getSINT16(ByteBuffer var0) {
      return var0.getShort();
   }

   public static final long getSINT32(ByteBuffer var0) {
      return var0.getInt();
   }

   public static final int getUINT8(ByteBuffer var0) {
      byte var1 = var0.get();
      return var1 == -1 ? var1 : var1 & 0xFF;
   }

   public static final int getUINT16(ByteBuffer var0) {
      short var1 = var0.getShort();
      return var1 == -1 ? var1 : var1 & 65535;
   }

   public static final long getUINT32(ByteBuffer var0) {
      long var1 = var0.getInt();
      return var1 == -1L ? var1 : var1 & 4294967295L;
   }

   public static byte[] parseCString(ByteBuffer var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      while (true) {
         byte var2 = var0.get();
         if (var2 == 0) {
            return var1.toByteArray();
         }

         var1.write(var2);
      }
   }
}
