package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class sw extends Rx {
   private final int wS;
   private final wR UT;

   protected sw(ByteBuffer var1, @Nullable Ij var2) {
      super(var1, var2);
      this.wS = var1.getInt();
      this.UT = wR.pC(var1);
   }

   @Override
   protected Ij.Av A() {
      return Ij.Av.ld;
   }

   @Override
   protected void pC(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.pC(var1, var2, var3);
      var1.writeInt(this.wS);
      var1.write(this.UT.wS());
   }
}
