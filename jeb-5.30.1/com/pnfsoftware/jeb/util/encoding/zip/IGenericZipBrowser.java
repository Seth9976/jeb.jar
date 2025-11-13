package com.pnfsoftware.jeb.util.encoding.zip;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface IGenericZipBrowser extends AutoCloseable {
   @Override
   void close() throws IOException;

   Map getEntries();

   GenericZipEntry getEntry(String var1);

   byte[] readEntry(String var1) throws IOException;

   InputStream getEntryStream(String var1) throws IOException;
}
