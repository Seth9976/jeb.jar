package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

@Ser
public class oP {
   @SerId(1)
   private long pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private int kS;
   @SerId(4)
   private int wS;
   @SerId(5)
   private int UT;

   public static oP pC(ByteBuffer var0, long var1) {
      oP var3 = new oP();
      var3.pC = var1;
      var3.A = var0.getInt();
      var3.kS = var0.getInt();
      var3.wS = var0.getInt();
      var3.UT = var0.getInt();
      return var3;
   }

   public int pC(SeekableByteChannel var1, boolean var2, boolean var3) throws IOException {
      ByteOrder var4 = var3 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
      long var5 = this.pC + 16L + this.wS * (var2 ? 8 : 4);
      var1.position(var5);
      int var7 = 0;

      for (int var8 = 0; var8 < this.A; var8++) {
         int var9 = ChannelUtil.getInt(var1, var4);
         if (var9 > var7) {
            var7 = var9;
         }
      }

      int var13 = var7 - this.kS;
      long var14 = var5 + this.A * 4;
      var1.position(var14 + var13 * 4);

      for (int var11 = ChannelUtil.getInt(var1, var4); (var11 & 1) == 0; var13++) {
         var11 = ChannelUtil.getInt(var1, var4);
      }

      return var13 + 1 + this.kS;
   }
}
