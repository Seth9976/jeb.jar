package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDalvikInstructionArrayData {
   int getOffset();

   byte[][] getElements();

   long[] asArrayOfLongs();
}
