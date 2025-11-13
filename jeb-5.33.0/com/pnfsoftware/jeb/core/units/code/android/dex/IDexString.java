package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeString;

public interface IDexString extends ICodeString {
   String getValue(boolean var1);

   void setValue(String var1);

   Boolean getHintUsedInDex();

   Boolean getHintUsedInDex(Boolean var1);

   Boolean getHintUsedAsImmediate();
}
