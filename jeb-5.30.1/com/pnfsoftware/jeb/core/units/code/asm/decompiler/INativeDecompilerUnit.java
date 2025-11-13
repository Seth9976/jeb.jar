package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeDecompilerUnit extends IDecompilerUnit, INativeDecompilerContext {
   INativeCodeUnit getCodeUnit();

   INativeSourceUnit decompileToUnit(String var1);

   INativeSourceUnit decompileToUnit(String var1, DecompilationContext var2);

   INativeSourceUnit getDecompiledUnit(String var1);

   INativeSourceUnit decompileToUnit(INativeItem var1);

   INativeSourceUnit decompileToUnit(INativeItem var1, DecompilationContext var2);

   INativeSourceUnit getDecompiledUnit(INativeItem var1);

   IDecompiledMethod decompileMethodEx(INativeMethodItem var1, DecompilationContext var2, NativeDecompilationStage var3);

   @Override
   IEConverter getConverter();

   @Override
   INativeDecompilerExtensionsManager getExtensionsManager();
}
