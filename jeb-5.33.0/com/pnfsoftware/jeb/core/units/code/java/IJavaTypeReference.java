package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaTypeReference extends IJavaExpression {
   IJavaType getType();

   void setType(IJavaType var1);

   IJavaTypeReference duplicate();
}
