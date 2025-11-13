package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.ISourceUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;

public interface INativeSourceUnit extends ISourceUnit {
   INativeDecompilerUnit getDecompiler();

   IDecompiledItem getDecompiledItem();

   INativeItem getNativeItem();

   ICElement getASTItem();
}
