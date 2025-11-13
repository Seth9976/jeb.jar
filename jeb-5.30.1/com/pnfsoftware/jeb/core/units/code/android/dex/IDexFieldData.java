package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexFieldData {
   int getFieldIndex();

   int getAccessFlags();

   boolean isPublic();

   boolean isProtected();

   boolean isPrivate();

   boolean isStatic();

   boolean isFinal();

   boolean isSynthetic();

   boolean isVolatile();

   int getDexEntryIndex();

   default int getDexFileIndex() {
      return this.getDexEntryIndex();
   }

   int getUserFlags();

   void setUserFlags(int var1);
}
