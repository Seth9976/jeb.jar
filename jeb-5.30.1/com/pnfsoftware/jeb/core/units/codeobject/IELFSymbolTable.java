package com.pnfsoftware.jeb.core.units.codeobject;

import java.util.List;

public interface IELFSymbolTable {
   int getCountOfEntries();

   List getEntries();

   IELFSymbolEntry getEntry(int var1);

   int getSectionIndex();

   boolean isDynamic();

   String format(IELFUnit var1);
}
