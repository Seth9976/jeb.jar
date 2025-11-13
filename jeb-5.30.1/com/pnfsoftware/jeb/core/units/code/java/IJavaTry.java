package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaTry extends IJavaCompound {
   List getResourceAcqs();

   void addResourceAcq(IJavaExpression var1);

   void addResourceAcq(int var1, IJavaExpression var2);

   void removeResourceAcq(int var1);

   boolean isTryWithResource();

   IJavaBlock getTryBody();

   void setTryBody(IJavaBlock var1);

   int getCatchCount();

   IJavaCatchBlock getCatchBlock(int var1);

   IJavaCatchBlock removeCatchBlock(int var1);

   IJavaBlock getCatchBody(int var1);

   IJavaType getCatchType(int var1);

   IJavaIdentifier getCatchIdentifier(int var1);

   int getCatchByType(String var1);

   IJavaBlock getFinallyBlock();

   void setFinallyBlock(IJavaBlock var1);

   boolean hasFinallyBlock();

   void addCatchBlock(int var1, IJavaType var2, List var3, IJavaIdentifier var4, IJavaDefinition var5, IJavaBlock var6);

   void addCatchBlock(IJavaType var1, List var2, IJavaIdentifier var3, IJavaDefinition var4, IJavaBlock var5);

   IJavaTry duplicate();
}
