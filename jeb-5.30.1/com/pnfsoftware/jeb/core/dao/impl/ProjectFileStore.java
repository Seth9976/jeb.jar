package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.core.dao.IFileDatabase;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseReader;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseWriter;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProjectFileStore implements IFileDatabase {
   private IFileStore fstore;

   public ProjectFileStore(IFileStore var1) {
      this.fstore = var1;
   }

   @Override
   public boolean hasFile(String var1) {
      return this.fstore.has(var1);
   }

   @Override
   public boolean saveFile(String var1, byte[] var2) {
      return this.fstore.put(var1, var2) != null;
   }

   @Override
   public byte[] loadFile(String var1) {
      return this.fstore.get(var1);
   }

   @Override
   public boolean deleteFile(String var1) {
      return this.fstore.remove(var1);
   }

   @Override
   public File getFileObject(String var1) {
      throw new UnsupportedOperationException("Not implemented");
   }

   @Override
   public OutputStream getFileWriter(String var1) throws IOException {
      throw new UnsupportedOperationException("Not implemented");
   }

   @Override
   public InputStream getFileReader(String var1) throws IOException {
      throw new UnsupportedOperationException("Not implemented");
   }

   @Override
   public IFileDatabaseWriter getDatabaseWriter(String var1) throws IOException {
      throw new UnsupportedOperationException("Not implemented");
   }

   @Override
   public IFileDatabaseReader getDatabaseReader(String var1) throws IOException {
      throw new UnsupportedOperationException("Not implemented");
   }
}
