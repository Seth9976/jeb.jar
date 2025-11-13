package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFRelocationEntry {
   long getOffset();

   long getInfo();

   boolean isAddendSet();

   long getAddend();

   int getSymbolIndex();

   int getType();

   String format(IELFUnit var1, IELFSymbolTable var2);
}
