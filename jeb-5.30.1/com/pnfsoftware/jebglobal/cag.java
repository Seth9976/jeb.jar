package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.InputStream;

class cag extends InputStream {
   cag(cad var1) {
      this.q = var1;
   }

   @Override
   public int read() throws IOException {
      return -1;
   }
}
