package com.pnfsoftware.jeb.util.serialization;

import java.io.IOException;
import java.io.OutputStream;

public interface IInternalSerializer {
   void writeFields(Object var1, Class var2) throws IOException;

   void write(Object var1) throws IOException;

   OutputStream getStream();
}
