package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;

public interface IDField extends IDExpression {
   IDIndex getIndex();

   String getFieldname();

   IDexField getNativeField(IDGlobalContext var1);

   IDField duplicate();
}
