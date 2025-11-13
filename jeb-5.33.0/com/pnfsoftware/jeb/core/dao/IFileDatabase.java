package com.pnfsoftware.jeb.core.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IFileDatabase {
   boolean hasFile(String var1);

   boolean saveFile(String var1, byte[] var2);

   byte[] loadFile(String var1);

   boolean deleteFile(String var1);

   File getFileObject(String var1);

   OutputStream getFileWriter(String var1) throws IOException;

   InputStream getFileReader(String var1) throws IOException;

   IFileDatabaseWriter getDatabaseWriter(String var1) throws IOException;

   IFileDatabaseReader getDatabaseReader(String var1) throws IOException;
}
