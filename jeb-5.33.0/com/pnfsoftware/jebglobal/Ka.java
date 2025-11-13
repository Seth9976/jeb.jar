package com.pnfsoftware.jebglobal;

import com.google.common.primitives.UnsignedBytes;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class Ka extends Ij {
   private final int wS;
   private final int[] UT;

   protected Ka(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = UnsignedBytes.toInt(var1.get());
      var1.position(var1.position() + 3);
      int var3 = var1.getInt();
      this.UT = new int[var3];

      for (int var4 = 0; var4 < var3; var4++) {
         this.UT[var4] = var1.getInt();
      }
   }

   public int UT() {
      return this.wS;
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.WR;
   }

   @Override
   protected void kS(ByteBuffer var1) {
      super.kS(var1);
      var1.putInt(this.wS);
      var1.putInt(this.UT.length);
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      for (int var7 : this.UT) {
         var1.writeInt(var7);
      }
   }
}
