package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDInstanceField extends IDField {
   IDExpression getInstance();

   boolean isArrayLength();

   IDInstanceField duplicate();
}
