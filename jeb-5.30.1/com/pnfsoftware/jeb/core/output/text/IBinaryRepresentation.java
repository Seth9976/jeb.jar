package com.pnfsoftware.jeb.core.output.text;

public interface IBinaryRepresentation {
   long getBaseOffsetHint();

   int read(long var1, int var3, byte[] var4, int var5);

   long find(long var1, long var3, byte[] var5, byte[] var6);

   ICoordinates convertOffsetToCoordinates(long var1);

   long convertCoordinatesToOffset(ICoordinates var1);
}
