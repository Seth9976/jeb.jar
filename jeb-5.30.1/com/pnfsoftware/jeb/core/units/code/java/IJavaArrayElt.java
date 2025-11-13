package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaArrayElt extends IJavaLeftExpression {
   IJavaExpression getArray();

   void setArray(IJavaExpression var1);

   IJavaExpression getIndex();

   void setIndex(IJavaExpression var1);

   IJavaArrayElt duplicate();
}
