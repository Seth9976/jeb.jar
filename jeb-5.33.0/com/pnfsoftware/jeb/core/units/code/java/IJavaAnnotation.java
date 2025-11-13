package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaAnnotation extends IJavaExpression {
   IJavaType getType();

   void setType(IJavaType var1);

   List getElements();

   void generate(JavaOutputSink var1, char var2);

   IJavaAnnotation duplicate();
}
