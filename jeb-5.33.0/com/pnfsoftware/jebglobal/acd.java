package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;

public class acd extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.TYPES_APPLICATION) {
         asu var3 = new asu(var2.getIRContext());
         var3.pC();
         ast var4 = new ast(var2.getIRContext());
         var4.pC();
      }

      return super.executePrePipelineStage(var1, var2);
   }
}
