package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IPrimitiveSizes {
   int getCharSize();

   int getShortSize();

   int getIntSize();

   int getLongSize();

   int getLongLongSize();

   int getFloatSize();

   int getDoubleSize();

   int getLongDoubleSize();
}
