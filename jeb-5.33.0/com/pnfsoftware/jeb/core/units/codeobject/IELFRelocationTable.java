package com.pnfsoftware.jeb.core.units.codeobject;

import java.util.List;

public interface IELFRelocationTable {
   int getCountOfEntries();

   List getEntries();

   IELFRelocationEntry getEntry(int var1);

   int getSectionIndex();

   int getSymbolSectionIndex();

   int getTargetSectionIndex();

   long getFileOffset();

   String format(IELFUnit var1);
}
