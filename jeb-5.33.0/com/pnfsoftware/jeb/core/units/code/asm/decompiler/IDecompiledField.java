package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDecompiledField extends IDecompiledItem {
   INativeFieldItem getFieldItem();

   ICField getFieldAST();
}
