package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaAnnotationElement extends IJavaElement {
   IJavaConstant getName();

   void setName(IJavaConstant var1);

   IJavaExpression getValue();

   void setValue(IJavaExpression var1);

   IJavaAnnotationElement duplicate();
}
