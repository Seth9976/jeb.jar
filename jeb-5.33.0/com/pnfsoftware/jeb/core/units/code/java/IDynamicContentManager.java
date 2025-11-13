package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.units.code.StringInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import java.util.List;

public interface IDynamicContentManager {
   IDexType getDexType(String var1);

   boolean isAnonymousClass(IJavaType var1);

   boolean generateType(JavaOutputSink var1, IJavaType var2, boolean var3, boolean var4, long var5);

   String generatePackageName(JavaOutputSink var1, IJavaType var2);

   String getComment(ICodeCoordinates var1);

   String getPreComment(ICodeCoordinates var1);

   String getMethodSignature(int var1);

   String getMethodName(int var1);

   boolean wasMethodRenamed(int var1);

   long getMethodId(int var1);

   long getBestVirtualMethodId(int var1, IJavaType var2, List var3);

   long getImplStaticMethodId(int var1);

   String getFieldSignature(int var1);

   String getFieldName(int var1);

   boolean wasFieldRenamed(int var1);

   long getFieldId(int var1);

   long getImplFieldId(int var1);

   String getLabelName(ICodeCoordinates var1);

   long getLabelId(ICodeCoordinates var1);

   String getIdentifierName(IdentifierCoordinates var1);

   long getIdentifierId(IdentifierCoordinates var1);

   StringInfo getStringInfo(String var1);

   long getImmediateId(long var1);

   int getImmediatePreferredBase(long var1);

   String retrieveImmediateHint(long var1);

   List findTypesWithSuperMethods(int var1);

   String getDecryptorData(int var1);

   boolean isCollapsed(String var1, String[] var2);
}
