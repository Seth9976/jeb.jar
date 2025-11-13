package com.pnfsoftware.jeb.core.dao;

import com.pnfsoftware.jeb.core.dao.impl.JDB2Reader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.List;

public interface IFileDatabaseReader extends Closeable {
   boolean hasBackingFile();

   File getBackingFile() throws NoSuchFileException;

   List getRecordDescriptions();

   JDB2Reader.RecordDescription getRecordDescription(int var1);

   InputStream getRecord(int var1) throws IOException;

   InputStream getRecord(JDB2Reader.RecordDescription var1) throws IOException;
}
