package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class sy {
   int q;
   int RF;
   int xK;
   HK Dw;

   sy(int var1, int var2, int var3, HK var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public sy(uL var1) throws IOException {
      this.q = var1.readInt();
      this.RF = var1.readInt();
      this.xK = var1.readInt();
      this.Dw = new HK(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("ns=%d,name=%d,raw=0x%X,val={%s}", this.q, this.RF, this.xK, this.Dw);
   }

   public void q(pK var1) {
      var1.q.writeIntLE(this.q);
      var1.q.writeIntLE(this.RF);
      var1.q.writeIntLE(this.xK);
      this.Dw.q(var1);
   }
}
