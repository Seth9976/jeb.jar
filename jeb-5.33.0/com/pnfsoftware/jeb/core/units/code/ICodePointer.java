package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICodePointer {
   long getAddress();

   int getMode();

   boolean isUnknownAddress();

   @Override
   String toString();
}
