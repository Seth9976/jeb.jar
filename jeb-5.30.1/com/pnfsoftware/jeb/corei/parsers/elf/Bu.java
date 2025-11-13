package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

@Ser
public class Bu {
   @SerId(1)
   private long q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private int xK;
   @SerId(4)
   private int Dw;
   @SerId(5)
   private int Uv;

   public static Bu q(ByteBuffer var0, long var1) {
      Bu var3 = new Bu();
      var3.q = var1;
      var3.RF = var0.getInt();
      var3.xK = var0.getInt();
      var3.Dw = var0.getInt();
      var3.Uv = var0.getInt();
      return var3;
   }

   public int q(SeekableByteChannel var1, boolean var2, boolean var3) throws IOException {
      ByteOrder var4 = var3 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
      long var5 = this.q + 16L + this.Dw * (var2 ? 8 : 4);
      var1.position(var5);
      int var7 = 0;

      for (int var8 = 0; var8 < this.RF; var8++) {
         int var9 = ChannelUtil.getInt(var1, var4);
         if (var9 > var7) {
            var7 = var9;
         }
      }

      int var13 = var7 - this.xK;
      long var14 = var5 + this.RF * 4;
      var1.position(var14 + var13 * 4);

      for (int var11 = ChannelUtil.getInt(var1, var4); (var11 & 1) == 0; var13++) {
         var11 = ChannelUtil.getInt(var1, var4);
      }

      return var13 + 1 + this.xK;
   }
}
