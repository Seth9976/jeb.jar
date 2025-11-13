package com.pnfsoftware.jeb.core.units.code.wincommon;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Random;

@Ser
public class Guid {
   @SerId(1)
   byte[] bytes;

   public static Guid parse(ByteBuffer var0) {
      byte[] var1 = new byte[16];
      var0.get(var1);
      return new Guid(var1, true);
   }

   public static Guid parse(String var0, boolean var1) {
      String var2 = var0.replace("-", "").replace("{", "").replace("}", "").trim();
      byte[] var3 = Formatter.hexStringToByteArray(var2);
      if (var3 == null || var3.length != 16) {
         throw new IllegalArgumentException("Cannot parse as a GUID: " + var0);
      } else if (!var1) {
         return new Guid(var3);
      } else {
         ByteBuffer var4 = ByteBuffer.wrap(var3);
         var4.order(var1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
         int var5 = var4.getInt();
         short var6 = var4.getShort();
         short var7 = var4.getShort();
         byte[] var8 = new byte[8];
         var4.get(var8);
         ByteBuffer var9 = ByteBuffer.allocate(16);
         var9.putInt(var5);
         var9.putShort(var6);
         var9.putShort(var7);
         var9.put(var8);
         return new Guid(var9.array());
      }
   }

   public static Guid random() {
      Random var0 = new Random(System.currentTimeMillis());
      byte[] var1 = new byte[16];
      var0.nextBytes(var1);
      return new Guid(var1, true);
   }

   public Guid(byte[] var1, int var2) {
      this.bytes = Arrays.copyOfRange(var1, var2, var2 + 16);
   }

   public Guid(byte[] var1) {
      this(var1, false);
   }

   public Guid(byte[] var1, boolean var2) {
      if (var2) {
         if (var1.length != 16) {
            throw new IllegalArgumentException();
         }

         this.bytes = var1;
      } else {
         if (var1.length < 16) {
            throw new IllegalArgumentException();
         }

         this.bytes = Arrays.copyOf(var1, 16);
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + Arrays.hashCode(this.bytes);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         Guid var2 = (Guid)var1;
         return Arrays.equals(this.bytes, var2.bytes);
      }
   }

   @Override
   public String toString() {
      return this.toString(true, true, true);
   }

   public String toString(boolean var1, boolean var2, boolean var3) {
      ByteBuffer var4 = ByteBuffer.wrap(this.bytes);
      if (var1) {
         var4.order(ByteOrder.LITTLE_ENDIAN);
      }

      String var5 = "";
      if (var2) {
         var5 = "-";
      }

      String var6 = "";
      String var7 = "";
      if (var3) {
         var6 = "{";
         var7 = "}";
      }

      int var8 = var4.getInt();
      short var9 = var4.getShort();
      short var10 = var4.getShort();
      byte var11 = var4.get();
      byte var12 = var4.get();
      byte var13 = var4.get();
      byte var14 = var4.get();
      byte var15 = var4.get();
      byte var16 = var4.get();
      byte var17 = var4.get();
      byte var18 = var4.get();
      return Strings.ff(
         "%s%08X%s%04X%s%04X%s%02X%02X%02X%02X%02X%02X%02X%02X%s",
         var6,
         var8,
         var5,
         var9,
         var5,
         var10,
         var5,
         var11,
         var12,
         var13,
         var14,
         var15,
         var16,
         var17,
         var18,
         var7
      );
   }
}
