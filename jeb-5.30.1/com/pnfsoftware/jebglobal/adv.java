package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;

public class adv extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.TYPES_APPLICATION) {
         avm var3 = new avm(var2.getIRContext());
         var3.xK();
         avl var4 = new avl(var2.getIRContext());
         var4.xK();
      }

      return super.executePrePipelineStage(var1, var2);
   }
}
