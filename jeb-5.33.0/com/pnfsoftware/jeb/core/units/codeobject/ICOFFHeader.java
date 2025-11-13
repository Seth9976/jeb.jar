package com.pnfsoftware.jeb.core.units.codeobject;

public interface ICOFFHeader {
   int getMachine();

   int getNumberOfSections();

   int getTimeDateStamp();

   int getPointerToSymbolTable();

   int getNumberOfSymbols();

   int getSizeOfOptionalHeader();

   int getCharacteristics();

   long getTimestampMs();
}
