package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDecompiledClass extends IDecompiledItem {
   INativeClassItem getClassItem();

   ICClass getClassAST();
}
