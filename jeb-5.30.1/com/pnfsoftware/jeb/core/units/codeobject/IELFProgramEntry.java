package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFProgramEntry {
   int getType();

   long getOffset();

   long getVirtualAddress();

   long getPhysicalAddress();

   long getSize();

   long getVirtualSize();

   long getAlignment();

   int getFlags();

   String format(IELFUnit var1);
}
