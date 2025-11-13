package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import java.util.List;

public interface INativeDisassemblerPlugin extends INativePlugin {
   boolean canBeProcessedOutsideCodeObject();

   boolean canProcessELF(int var1, boolean var2);

   boolean canProcessPE(int var1, boolean var2);

   List getProcessorTypes();

   IProcessor getProcessor(IUnitCreator var1);

   ICallingConvention getCallingConvention(IUnitCreator var1);

   IVirtualMemory getMemory(IUnitCreator var1);

   GenericCodeFormatter getCodeFormatter();

   INativeCodeAnalyzerExtension getAnalyzerExtension();
}
