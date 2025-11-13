package com.pnfsoftware.jebglobal;

import java.io.IOException;

public final class WG extends Hy {
   int lm;
   int zz;

   public WG(jA var1, uL var2, boolean var3) throws IOException {
      super(var1, var2, var3);
      this.lm = var2.readInt();
      this.zz = var2.readInt();
   }

   @Override
   public void q(pK var1) {
      super.q(var1);
      var1.q.writeIntLE(this.lm);
      var1.q.writeIntLE(this.zz);
      this.RF(var1);
   }
}
