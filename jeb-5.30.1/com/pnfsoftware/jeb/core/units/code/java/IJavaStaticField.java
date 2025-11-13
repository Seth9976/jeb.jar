package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaStaticField extends IJavaLeftExpression {
   IJavaType getClassType();

   String getFieldSignature();

   String getFieldName();

   boolean isPseudoFieldTypeClass();

   IJavaField getField();

   IJavaStaticField duplicate();
}
