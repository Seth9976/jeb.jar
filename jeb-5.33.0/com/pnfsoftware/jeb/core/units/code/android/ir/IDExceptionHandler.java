package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDExceptionHandler {
   int getTypeIndex();

   void setTypeIndex(int var1);

   boolean isTyped();

   int getAddress();

   void setAddress(int var1);

   boolean isCatchAll(IDMethodContext var1);

   boolean isCatchAll(IDMethodContext var1, boolean var2);
}
