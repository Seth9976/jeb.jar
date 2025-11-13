package com.pnfsoftware.jeb.core.units.codeobject;

public interface ICOFFSectionHeader {
   byte[] getName();

   long getVirtualSize();

   long getVirtualAddress();

   long getSizeOfRawData();

   long getPointerToRawData();

   long getPointerToRelocations();

   long getPointerToLinenumbers();

   int getNumberOfRelocations();

   int getNumberOfLinenumbers();

   int getCharacteristics();
}
