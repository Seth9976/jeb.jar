package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaInstanceField extends IJavaLeftExpression {
   IJavaExpression getInstance();

   void setInstance(IJavaExpression var1);

   String getFieldSignature();

   String getFieldName();

   boolean isPseudoFieldArrayLength();

   IJavaField getField();

   IJavaInstanceField duplicate();
}
