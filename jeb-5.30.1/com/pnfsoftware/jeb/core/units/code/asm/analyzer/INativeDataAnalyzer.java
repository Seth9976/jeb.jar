package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;

public interface INativeDataAnalyzer {
   ITypeManager getTypeManager();

   INativeDataItem apply(long var1, INativeType var3);

   INativeDataItem apply(long var1, INativeType var3, int var4);

   INativeStringItem createString(long var1, long var3, INativeType var5, StringEncoding var6, String var7);
}
