package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexAddress {
   String getInternalAddress();

   String getUserAddress();

   long getEncodedAddress();

   DexReferenceType getReferenceType();

   String getInfo();

   int getFlags();
}
