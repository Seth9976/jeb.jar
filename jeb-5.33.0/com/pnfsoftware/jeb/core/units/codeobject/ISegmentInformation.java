package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ISegmentInformation {
   int FLAG_WRITE = 1;
   int FLAG_READ = 2;
   int FLAG_EXECUTE = 4;
   int FLAG_RW = 3;
   int FLAG_RX = 6;
   int FLAG_RWX = 7;
   int FLAG_INVALID = 536870912;
   int FLAG_ALLOC_ON_WRITE = 1073741824;
   int FLAG_SYNTHETIC = Integer.MIN_VALUE;

   String getName();

   long getOffsetInFile();

   long getSizeInFile();

   long getOffsetInMemory();

   long getSizeInMemory();

   int getFlags();

   long getAlignment();
}
