package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDArrayElt extends IDExpression {
   IDExpression getArray();

   IDExpression getIndex();

   IDArrayElt duplicate();
}
