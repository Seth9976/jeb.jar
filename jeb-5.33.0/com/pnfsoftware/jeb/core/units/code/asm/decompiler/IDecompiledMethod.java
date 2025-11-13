package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDecompiledMethod extends IDecompiledItem {
   INativeMethodItem getMethodItem();

   ICMethod getMethodAST();

   NativeDecompilationStage getCompletedStage();

   IEConverter getConverter();

   IEMasterOptimizer getIROptimizer();

   IERoutineContext getIRContext();

   ICMasterOptimizer getASTOptimizer();
}
