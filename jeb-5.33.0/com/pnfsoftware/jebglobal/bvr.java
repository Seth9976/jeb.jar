package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.InputStream;

class bvr extends InputStream {
   bvr(bvo var1) {
      this.pC = var1;
   }

   @Override
   public int read() throws IOException {
      return -1;
   }
}
