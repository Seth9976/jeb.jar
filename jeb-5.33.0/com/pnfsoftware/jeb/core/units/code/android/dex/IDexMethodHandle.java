package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexMethodHandle extends IDexItem {
   DexMethodHandleType getMethodHandleType();

   int getFieldOrMethodIndex();

   String generate(boolean var1);
}
