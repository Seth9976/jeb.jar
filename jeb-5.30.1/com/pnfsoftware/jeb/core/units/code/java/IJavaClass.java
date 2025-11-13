package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaClass extends IJavaDecompilableElement {
   int getAccessFlags();

   boolean isInner();

   boolean isAnonymous();

   boolean isStatic();

   boolean isInterface();

   boolean isEnumeration();

   boolean isAnnotation();

   boolean isSynthetic();

   @Override
   String getName();

   IJavaType getType();

   @Override
   String getSignature();

   IJavaType getSupertype();

   List getImplementedInterfaces();

   List getAnnotations();

   List getInnerClassSignatures();

   List getInnerClasses();

   List getAnonymousClassSignatures();

   List getAnonymousClasses();

   List getFieldSignatures();

   List getFields();

   List getMethodsSignatures();

   List getMethods();

   boolean addImport(String var1, String var2);

   String getImport(String var1);

   void generate(JavaOutputSink var1, List var2, int var3, boolean var4);

   IJavaClass duplicate();
}
