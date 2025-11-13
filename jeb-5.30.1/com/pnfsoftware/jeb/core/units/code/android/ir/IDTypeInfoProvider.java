package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import java.util.List;

public interface IDTypeInfoProvider {
   boolean isCompatible(String var1, String var2);

   boolean isChildOf(String var1, String var2);

   List findTypesWithSuperMethods(String var1, String var2, String var3, boolean var4);

   String isFunctionalType(String var1);

   IJLSType findType(String var1);

   IJLSMethod findMethod(String var1, String var2, String var3);

   IJLSMethod findMethod(String var1, String var2, String var3, boolean var4, String[] var5);

   IJLSField findField(String var1, String var2);

   IJLSField findField(String var1, String var2, boolean var3, String[] var4);
}
