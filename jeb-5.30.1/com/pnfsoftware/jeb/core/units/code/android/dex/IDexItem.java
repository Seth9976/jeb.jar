package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;

public interface IDexItem extends ICodeItem {
   IDexUnit getDex();

   boolean isRenamed();
}
