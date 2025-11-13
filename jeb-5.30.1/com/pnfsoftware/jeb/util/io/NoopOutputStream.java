package com.pnfsoftware.jeb.util.io;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class NoopOutputStream extends FilterOutputStream {
   public NoopOutputStream(OutputStream var1) {
      super(var1);
   }
}
