package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaForEach extends IJavaCompound {
   IJavaDefinition getVariable();

   IJavaExpression getIterable();

   IJavaBlock getBody();

   void setVariable(IJavaDefinition var1);

   void setIterable(IJavaExpression var1);

   void setBody(IJavaBlock var1);

   IJavaForEach duplicate();
}
