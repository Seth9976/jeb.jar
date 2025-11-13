package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class Jj extends zW {
   private final int oW;
   private final Gn gO;

   protected Jj(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = var1.getInt();
      this.gO = Gn.q(var1);
   }

   public String gO() {
      return this.RF(this.oW);
   }

   public Gn nf() {
      return this.gO;
   }

   @Override
   protected oV.eo RF() {
      return oV.eo.gP;
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      super.q(var1, var2, var3);
      var1.writeInt(this.oW);
      var1.write(this.gO.oW());
   }
}
