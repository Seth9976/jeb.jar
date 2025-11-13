package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class o extends Rx {
   private final int wS;
   private final int UT;

   protected o(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = var1.getInt();
      this.UT = var1.getInt();
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.ys;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.pC(var1, var2, var3);
      var1.writeInt(this.wS);
      var1.writeInt(this.UT);
   }
}
