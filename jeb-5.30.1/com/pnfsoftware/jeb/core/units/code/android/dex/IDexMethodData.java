package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexMethodData {
   int getMethodIndex();

   int getAccessFlags();

   void setInlineMode(int var1);

   int getInlineMode();

   boolean isConstructor();

   boolean isStatic();

   boolean isAbstract();

   boolean isNative();

   boolean isSynthetic();

   boolean isPublic();

   boolean isProtected();

   boolean isPrivate();

   boolean isFinal();

   IDexCodeItem getCodeItem();

   int[] getExceptionTypeIndices();

   int getDexEntryIndex();

   default int getDexFileIndex() {
      return this.getDexEntryIndex();
   }

   int getUserFlags();

   void setUserFlags(int var1);
}
