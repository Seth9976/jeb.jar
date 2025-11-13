package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDStaticField extends IDField {
   String getClassSignature();

   boolean isTypeClass();

   IDStaticField duplicate();
}
