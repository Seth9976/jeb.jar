package com.pnfsoftware.jeb.util.io;

import java.io.FilterInputStream;
import java.io.InputStream;

public class NoopInputStream extends FilterInputStream {
   public NoopInputStream(InputStream var1) {
      super(var1);
   }
}
