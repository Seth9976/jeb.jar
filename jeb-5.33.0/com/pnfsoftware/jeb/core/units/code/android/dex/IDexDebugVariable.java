package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDexDebugVariable {
   int getAddress();

   int getRegister();

   int getNameIndex();

   int getTypeIndex();

   int getSignatureIndex();

   String format(IDexUnit var1);
}
