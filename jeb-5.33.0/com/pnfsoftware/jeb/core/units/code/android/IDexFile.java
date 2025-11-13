package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMap;
import com.pnfsoftware.jeb.util.io.Endianness;

public interface IDexFile {
   byte[] getData();

   int getOffset();

   int getFileIndex();

   int getIndexInContainer();

   IDexUnit getOwnerUnit();

   int getVersion();

   int getHeaderSize();

   Endianness getEndianness();

   int getExpectedSize();

   default int getExpectedFileSize() {
      return this.getExpectedSize();
   }

   int getExpectedChecksum();

   int getActualChecksum();

   byte[] getExpectedSignature();

   byte[] getActualSignature();

   int getLinkSectionSize();

   int getLinkSectionOffset();

   int getMapOffset();

   IDexMap getMap();

   int getStringsPoolSize();

   int getStringsPoolOffset();

   int getTypesPoolSize();

   int getTypesPoolOffset();

   int getPrototypesPoolSize();

   int getPrototypesPoolOffset();

   int getFieldsPoolSize();

   int getFieldsPoolOffset();

   int getMethodsPoolSize();

   int getMethodsPoolOffset();

   int getClassesPoolSize();

   int getClassesPoolOffset();

   int getDataSectionSize();

   int getDataSectionOffset();

   int getCallSitesPoolSize();

   int getCallSitesPoolOffset();

   int getMethodHandlesPoolSize();

   int getMethodHandlesPoolOffset();
}
