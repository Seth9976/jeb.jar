package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;

public interface IDexExceptionHandler {
   int getTypeIndex();

   int getAddress();

   IDexType resolveType(IDexUnit var1);
}
