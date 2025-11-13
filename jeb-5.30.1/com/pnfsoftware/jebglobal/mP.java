package com.pnfsoftware.jebglobal;

import java.io.IOException;

public final class mP extends Hy {
   int lm;
   HK zz;
   byte[] JY;

   public mP(jA var1, uL var2, boolean var3) throws IOException {
      super(var1, var2, var3);
      this.lm = var2.readInt();
      this.zz = new HK(var2);
   }

   @Override
   public void q(pK var1) {
      super.q(var1);
      var1.q.writeIntLE(this.lm);
      this.zz.q(var1);
      this.RF(var1);
   }
}
