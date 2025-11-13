package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ISourceCustomizer;

public interface INativeDecompilerPlugin extends INativePlugin {
   IEConverter getConverter(INativeCodeUnit var1);

   INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1);

   IGlobalAnalyzer getGlobalAnalyzer(INativeDecompilerUnit var1);

   ISourceCustomizer getSourceCustomizer(INativeDecompilerUnit var1);
}
