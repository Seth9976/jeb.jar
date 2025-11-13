package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaMethod extends IJavaDecompilableElement {
   IJavaLabelFactory getLabelFactory();

   IJavaIdentifierManager getIdentifierManager();

   @Override
   String getName();

   @Override
   String getSignature();

   IJavaDefinition getParameter(int var1);

   int getParameterCount();

   void setIndexOfFirstVisibleParameter(int var1);

   int getIndexOfFirstVisibleParameter();

   int getVisibleParameterCount();

   List getVisibleParameters();

   List getParameters();

   void markSecondParameterOuterClassReference();

   boolean isSecondParameterOuterClassReference();

   IJavaType getReturnType();

   IJavaBlock getBody();

   boolean isEmpty();

   boolean isConstructor();

   boolean isClassConstructor();

   boolean isClassInitializer();

   int getAccessFlags();

   boolean isStatic();

   boolean isSynthetic();

   boolean isAbstract();

   boolean isNative();

   boolean isPublic();

   boolean isProtected();

   boolean isPrivate();

   boolean isInner();

   IJavaType getClassType();

   List getInnerClassSignatures();

   List getInnerClasses();

   List getAnonymousClassSignatures();

   List getAnonymousClasses();

   List getMethodAnnotations();

   List getParameterAnnotations(int var1);

   void setDefaultValue(IJavaExpression var1);

   IJavaExpression getDefaultValue();

   List getStatements();

   boolean deleteStatement(IJavaStatement var1);

   void generate(JavaOutputSink var1, boolean var2);

   void generate(JavaOutputSink var1, int var2);

   List toFlatList();

   void fromFlatList(List var1);

   IJavaMethod duplicate();
}
