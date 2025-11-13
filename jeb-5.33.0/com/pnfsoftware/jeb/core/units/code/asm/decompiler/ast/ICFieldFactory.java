package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICFieldFactory {
   ICField get(String var1);

   ICField create(String var1);

   ICField create(INativeFieldItem var1, boolean var2);

   ICField createStructureField(IStructureType var1, IStructureTypeField var2);
}
