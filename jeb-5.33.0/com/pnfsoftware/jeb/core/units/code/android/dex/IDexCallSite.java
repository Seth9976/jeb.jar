package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexCallSite extends IDexItem {
   List getCallSiteValues();

   IDexValue getCallSiteValue(int var1);

   int getLinkerMethodHandleIndex();

   IDexMethodHandle getLinkerMethodHandle();

   int getDynamicMethodNameIndex();

   IDexString getDynamicMethodName();

   int getDynamicMethodPrototypeIndex();

   IDexPrototype getDynamicMethodPrototype();

   String generate(boolean var1);
}
