package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.ICodeMethod;
import java.util.List;
import java.util.SortedMap;

public interface IDexMethod extends ICodeMethod, IDexItem {
   @Override
   int getIndex();

   IDexType getReturnType();

   @Override
   List getParameterTypes();

   IDexMethodData getData();

   IDexType getClassType();

   int getClassTypeIndex();

   String getClassTypeSignature(boolean var1);

   int getPrototypeIndex();

   IDexPrototype getPrototype();

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

   SortedMap getDebugVariablesMap();

   String getParameterName(int var1);

   boolean setParameterName(int var1, String var2, boolean var3, boolean var4);
}
