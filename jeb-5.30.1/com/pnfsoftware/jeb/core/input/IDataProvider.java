package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.IOException;
import java.io.InputStream;

@Ser
public interface IDataProvider {
   int NOT_IMPLEMENTED = -10;

   long getDataSize(String var1, int var2);

   byte[] getDataBytes(String var1, int var2);

   default int getDataBytes(String var1, int var2, byte[] var3, int var4, int var5) {
      return -10;
   }

   default InputStream openDataStream(String var1, int var2) throws IOException {
      return null;
   }
}
