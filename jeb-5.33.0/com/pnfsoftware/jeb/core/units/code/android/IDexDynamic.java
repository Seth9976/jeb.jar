package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IDexDynamic {
   void registerDynamicJni(String var1, IUnit var2, long var3);

   List getJniMethods(String var1);
}
