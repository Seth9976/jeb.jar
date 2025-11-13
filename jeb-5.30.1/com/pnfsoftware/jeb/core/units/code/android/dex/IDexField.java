package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeField;

public interface IDexField extends ICodeField, IDexItem {
   @Override
   int getIndex();

   IDexFieldData getData();

   IDexType getClassType();

   int getClassTypeIndex();

   String getClassTypeSignature(boolean var1);

   IDexType getFieldType();

   int getFieldTypeIndex();

   String getFieldTypeSignature(boolean var1);

   int getNameIndex();

   @Override
   String getName(boolean var1);

   @Override
   String getSignature(boolean var1);

   @Override
   String getSignature(boolean var1, boolean var2);

   String getSignature(boolean var1, boolean var2, boolean var3);

   String getSignature(boolean var1, boolean var2, boolean var3, boolean var4);

   boolean setName(String var1);

   IDexValue getStaticInitializer();
}
