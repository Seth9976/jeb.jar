package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ISymbolInformation {
   int FLAG_IMPORTED = 1;
   int FLAG_EXPORTED = 2;
   int FLAG_FUNCTION_CODE_CONTIGUOUS = 4;
   int FLAG_ABSOLUTE = 8;
   int FLAG_METADATA = 16;

   SymbolType getType();

   int getFlags();

   long getIdentifier();

   String getName();

   long getRelativeAddress();

   long getSymbolRelativeAddress();

   long getSymbolSize();

   String getSymbolDataTypeInformation();

   int getProcessorMode();
}
