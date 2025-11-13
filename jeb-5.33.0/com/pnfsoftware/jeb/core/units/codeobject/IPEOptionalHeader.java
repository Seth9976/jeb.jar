package com.pnfsoftware.jeb.core.units.codeobject;

public interface IPEOptionalHeader {
   short getMagic();

   byte getMajorLinkerVersion();

   byte getMinorLinkerVersion();

   long getSizeOfCode();

   long getSizeOfInitializedData();

   long getSizeOfUninitializedData();

   long getAddressOfEntryPoint();

   long getBaseOfCode();

   long getBaseOfData();

   long getImageBase();

   int getSectionAlignment();

   int getFileAlignment();

   short getMajorOperatingSystemVersion();

   short getMinorOperatingSystemVersion();

   short getMajorImageVersion();

   short getMinorImageVersion();

   short getMajorSubsystemVersion();

   short getMinorSubsystemVersion();

   int getWin32VersionValue();

   long getSizeOfImage();

   long getSizeOfHeaders();

   int getCheckSum();

   short getSubsystem();

   short getDllCharacteristics();

   long getSizeOfStackReserve();

   long getSizeOfStackCommit();

   long getSizeOfHeapReserve();

   long getSizeOfHeapCommit();

   int getLoaderFlags();

   long getNumberOfRvaAndSizes();

   IPEDataDirectory[] getDataDirectory();
}
