package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IMethodManager {
   int FLAG_PLACEHOLDER_METHOD = 1;
   int FLAG_DO_NOT_ANALYZE = 2;
   int FLAG_DO_NOT_UNMANGLE = 4;

   INativeMethodItem createMethodReference(String var1, IPrototypeItem var2, INativeMethodDataItem var3);

   INativeMethodItem createMethodReference(String var1, IPrototypeItem var2, INativeMethodDataItem var3, int var4);
}
