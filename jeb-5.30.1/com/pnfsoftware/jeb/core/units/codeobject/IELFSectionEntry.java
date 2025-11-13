package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFSectionEntry {
   String getName();

   int getType();

   long getFlags();

   long getAddress();

   long getOffset();

   long getSize();

   long getEntrySize();

   int getLink();

   int getInfo();

   long getAlignment();

   String format(IELFUnit var1);
}
