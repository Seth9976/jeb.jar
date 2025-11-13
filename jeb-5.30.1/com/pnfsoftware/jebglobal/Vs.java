package com.pnfsoftware.jebglobal;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class Vs extends oV {
   private final oV.eo oW;
   private final byte[] gO;
   private final byte[] nf;

   protected Vs(ByteBuffer var1, @Nullable oV var2) {
      super(var1, var2);
      this.oW = oV.eo.q(var1.getShort(this.Uv));
      this.gO = new byte[this.xK - 8];
      this.nf = new byte[this.Dw - this.xK];
      var1.get(this.gO);
      var1.get(this.nf);
   }

   @Override
   protected void xK(ByteBuffer var1) {
      var1.put(this.gO);
   }

   @Override
   protected void q(DataOutput var1, ByteBuffer var2, boolean var3) throws IOException {
      var1.write(this.nf);
   }

   @Override
   protected oV.eo RF() {
      return this.oW;
   }
}
