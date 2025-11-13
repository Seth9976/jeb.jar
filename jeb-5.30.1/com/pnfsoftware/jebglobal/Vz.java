package com.pnfsoftware.jebglobal;

import com.google.common.primitives.UnsignedBytes;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class Vz extends oV {
   private final int oW;
   private final int[] gO;

   protected Vz(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = UnsignedBytes.toInt(var1.get());
      var1.position(var1.position() + 3);
      int var3 = var1.getInt();
      this.gO = new int[var3];

      for (int var4 = 0; var4 < var3; var4++) {
         this.gO[var4] = var1.getInt();
      }
   }

   public int gO() {
      return this.oW;
   }

   public int nf() {
      return this.gO.length;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.JY;
   }

   @Override
   protected void xK(ByteBuffer var1) {
      super.xK(var1);
      var1.putInt(this.oW);
      var1.putInt(this.gO.length);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (int var7 : this.gO) {
         var1.writeInt(var7);
      }
   }
}
