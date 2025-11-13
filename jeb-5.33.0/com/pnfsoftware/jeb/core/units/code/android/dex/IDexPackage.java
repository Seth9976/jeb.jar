package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodePackage;

public interface IDexPackage extends ICodePackage, IDexItem {
   @Override
   String getName(boolean var1);

   @Override
   boolean setName(String var1);
}
