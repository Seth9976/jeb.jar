package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDReferenceType extends IDExpression {
   IDIndex getTypeIndex();

   IDReferenceType duplicate();
}
