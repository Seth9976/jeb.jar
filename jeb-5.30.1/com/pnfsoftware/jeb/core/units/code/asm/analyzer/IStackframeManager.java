package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IStackframeManager {
   IMethodStackMemoryModel getModel();

   INativeDataItem defineItem(long var1, INativeType var3);

   boolean undefineItem(long var1);

   int undefineItems(long var1, long var3);
}
