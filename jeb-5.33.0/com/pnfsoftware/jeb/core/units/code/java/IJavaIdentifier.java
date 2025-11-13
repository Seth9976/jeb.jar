package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaIdentifier extends IJavaLeftExpression {
   IJavaDefinition getDefinition();

   IJavaType getType();

   String getName();

   String getDebugName();

   String getGeneratedName(JavaOutputSink var1);

   String getGeneratedType(JavaOutputSink var1);

   IJavaIdentifier duplicate();

   void generate(JavaOutputSink var1, boolean var2);
}
