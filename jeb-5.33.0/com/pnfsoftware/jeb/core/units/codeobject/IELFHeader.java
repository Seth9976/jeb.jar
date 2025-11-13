package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.io.Endianness;

public interface IELFHeader {
   int getBitsize();

   Endianness getEndianness();

   int getOsabi();

   int getAbiVersion();

   int getType();

   int getMachine();

   long getEntry();

   long getProgramHeaderTableOffset();

   int getProgramHeaderTableEntrySize();

   int getProgramHeaderTableEntryNumber();

   long getSectionHeaderTableOffset();

   int getSectionHeaderTableEntrySize();

   int getSectionHeaderTableEntryNumber();

   int getFlags();

   int getHeaderSize();

   String format(IELFUnit var1);
}
