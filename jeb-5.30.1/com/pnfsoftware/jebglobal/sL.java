package com.pnfsoftware.jebglobal;

import java.io.IOException;

public class sL {
   int q;
   int RF;
   int xK;

   public sL(uL var1) throws IOException {
      this.q = var1.readInt();
      this.RF = var1.readInt();
      this.xK = var1.readInt();
   }
}
