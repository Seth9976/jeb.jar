package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Ser
public interface IJavaElement {
   int FLAG_BUILT = 1;
   int FLAG_SECOND_PARAMETER_IS_OUTER_REF = 2;
   int FLAG_OPTIONAL_RENDERING = 4;
   int FLAG_LAMBDA_IMPL = 16;
   int FLAG_LAMBDA_CLASS = 32;
   int FLAG_FIELD_REFERENCES_OUTERCLASS = 64;
   int FLAG_STICKY = 256;

   boolean hasPhysicalMethodIndex();

   void setPhysicalMethodIndex(int var1);

   int getPhysicalMethodIndex();

   boolean hasPhysicalOffset();

   int getPhysicalOffset();

   void setPhysicalOffset(int var1);

   void collectAllPhysicalOffsets(Collection var1);

   List getSubElements();

   boolean replaceSubElement(IJavaElement var1, IJavaElement var2);

   boolean canCauseException();

   void generate(JavaOutputSink var1);

   Map getTags();

   Object addTag(String var1, Object var2);

   Object removeTag(String var1);

   void setData(String var1, Object var2);

   Object getData(Object var1);

   void setFlags(int var1);

   int getFlags();

   void addFlags(int var1);

   void removeFlags(int var1);

   boolean hasFlags(int var1);

   JavaElementType getElementType();

   String toShortString();

   IJavaElement duplicate();

   boolean visitDepthPre(IJVisitor var1);

   boolean visitDepthPre(IJVisitor var1, IJavaElement var2);

   boolean visitDepthPre(IJVisitor var1, IJavaElement var2, JVisitResults var3);

   boolean visitDepthPost(IJVisitor var1);

   boolean visitDepthPost(IJVisitor var1, IJavaElement var2);

   boolean visitDepthPost(IJVisitor var1, IJavaElement var2, JVisitResults var3);

   boolean visitDepthPost(IJVisitor var1, IJavaElement var2, JVisitResults var3, boolean var4);

   boolean isReconArtifact();

   void setLambdaRecon(JavaReconLambda var1);

   JavaReconLambda getReconLambda();

   void setReconEnum(JavaReconEnum var1);

   JavaReconEnum getReconEnum();

   void setReconEnummap(JavaReconEnummap var1);

   JavaReconEnummap getReconEnummap();

   void setReconAnon(JavaReconAnon var1);

   JavaReconAnon getReconAnon();

   void setOrigin(String var1);

   String getOrigin();
}
