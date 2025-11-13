package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import java.util.List;
import java.util.Map;

public interface IDexClass extends ICodeClass, IDexItem {
   int getClassTypeIndex();

   int getSuperTypeIndex();

   String getSupertypeSignature(boolean var1);

   int[] getInterfaceTypeIndexes();

   String[] getInterfaceSignatures(boolean var1);

   int getAccessFlags();

   int getSourceStringIndex();

   @Override
   List getFields();

   @Override
   List getMethods();

   int getDexEntryIndex();

   default int getDexFileIndex() {
      return this.getDexEntryIndex();
   }

   IDexClassData getData();

   List getStaticInitializers();

   IDexAnnotationsDirectory getAnnotationsDirectory();

   boolean setName(String var1);

   boolean isMemberClass();

   boolean isAnonymousClass();

   boolean isNonStaticMemberClass();

   boolean isStaticMemberClass();

   IDexMethod getMethod(boolean var1, String var2, String... var3);

   IDexField getField(boolean var1, String var2, String var3);

   boolean isTrueClass();

   boolean isEnumeration();

   boolean isInterface();

   boolean isAnnotation();

   boolean isSynthetic();

   Map getAnnotationDefaults();
}
