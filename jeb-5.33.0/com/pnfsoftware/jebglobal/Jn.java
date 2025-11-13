package com.pnfsoftware.jebglobal;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Jn {
   static ByteBuffer pC(LittleEndianDataInputStream var0) throws IOException {
      return ByteBuffer.wrap(pC(var0, true)).order(ByteOrder.LITTLE_ENDIAN);
   }

   static byte[] pC(LittleEndianDataInputStream var0, boolean var1) throws IOException {
      int var2 = var1 ? 6 : 8;
      byte[] var3 = new byte[var2];
      var0.readFully(var3);
      ByteBuffer var4 = ByteBuffer.wrap(var3).order(ByteOrder.LITTLE_ENDIAN);

      for (int var5 = 0; var5 < var3.length - 4; var5++) {
         var4.get();
      }

      int var6 = var4.getInt();
      int var7 = var1 ? var6 - 2 : var6;
      byte[] var8 = Arrays.copyOf(var3, var7);
      var0.readFully(var8, var3.length, var6 - 8);
      return var8;
   }

   static ByteBuffer pC(LittleEndianDataInputStream var0, int var1) throws IOException {
      byte[] var2 = new byte[var1];
      var0.readFully(var2);
      return ByteBuffer.wrap(var2).order(ByteOrder.LITTLE_ENDIAN);
   }

   static Map pC(DataOutput var0, Collection var1, int var2, boolean var3) throws IOException {
      if (var3) {
         var0.writeInt(var1.size());
      }

      HashMap var4 = new HashMap();
      int var5 = 0;

      for (Ij var7 : var1) {
         byte[] var8 = var7.pC((var2 & 1) != 0);
         var0.write(var8);
         Ij.pC(var0, var8.length);
         var4.put(var7, var5);
         var5++;
      }

      return var4;
   }

   static ByteBuffer A(LittleEndianDataInputStream var0) throws IOException {
      byte[] var1 = new byte[6];
      var0.readFully(var1);
      short var2 = ByteBuffer.wrap(var1).order(ByteOrder.LITTLE_ENDIAN).getShort();
      byte[] var3 = Arrays.copyOf(var1, var2);
      var0.readFully(var3, 6, var2 - 8);
      return ByteBuffer.wrap(var3).order(ByteOrder.LITTLE_ENDIAN);
   }
}
