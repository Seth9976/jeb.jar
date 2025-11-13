package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFSymbolEntry {
   long getValue();

   long getSize();

   int getType();

   int getBinding();

   int getVisibility();

   String getName();

   long getAddress();

   boolean isExternal();

   int getShIndex();

   String format(IELFUnit var1);
}
