package com.pnfsoftware.jeb.core.dao;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;

public interface IFileDatabaseWriter extends Closeable {
   boolean hasBackingFile();

   File getBackingFile() throws NoSuchFileException;

   OutputStream beginRecord(int var1, int var2) throws IOException;

   void endRecord(OutputStream var1) throws IOException;

   void verify();
}
