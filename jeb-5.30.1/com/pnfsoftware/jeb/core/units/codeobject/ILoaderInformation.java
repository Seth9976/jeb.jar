package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ILoaderInformation {
   int FLAG_HAS_SYMBOLS = 1;
   int FLAG_HAS_RELOCATION = 2;
   int FLAG_LIBRARY_FILE = 4;
   int FLAG_OBJECT_FILE = 8;
   int FLAG_PURE_CODE = 16;
   int FLAG_IS_RELOCATABLE = 32;

   int getFlags();

   String getVersion();

   ProcessorType getTargetProcessor();

   SubsystemType getTargetSubsystem();

   Endianness getEndianness();

   int getWordSize();

   long getCompilationTimestamp();

   long getImageBase();

   long getImageSize();

   long getEntryPoint();

   long getOverlayOffset();

   String getNotes();
}
