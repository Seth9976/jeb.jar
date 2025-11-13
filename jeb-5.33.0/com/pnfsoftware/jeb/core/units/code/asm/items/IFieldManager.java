package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;

public interface IFieldManager {
   INativeFieldItem createField(INativeItem var1, String var2, INativeType var3);

   INativeFieldItem createField(INativeItem var1, INativeDataItem var2);
}
