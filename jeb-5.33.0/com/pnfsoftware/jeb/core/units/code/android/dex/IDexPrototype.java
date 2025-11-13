package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexPrototype {
   String generate(boolean var1);

   int getShortyIndex();

   String getShorty();

   int getReturnTypeIndex();

   int[] getParameterTypeIndexes();

   IDexType[] getParameterTypes();

   String[] getParameterSignatures(boolean var1);

   IDexType getReturnType();

   String getReturnTypeSignature(boolean var1);
}
