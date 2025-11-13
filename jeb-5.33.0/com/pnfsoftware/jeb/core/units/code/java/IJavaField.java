package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaField extends IJavaDecompilableElement {
   int getAccessFlags();

   boolean isStatic();

   boolean isSynthetic();

   IJavaType getClassType();

   IJavaType getType();

   @Override
   String getName();

   @Override
   String getSignature();

   IJavaExpression getInitialValue();

   List getAnnotations();

   void generateName(JavaOutputSink var1, boolean var2);

   void generateName(JavaOutputSink var1, boolean var2, String var3, boolean var4);

   IJavaField duplicate();
}
