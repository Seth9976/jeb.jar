package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;

public class aer extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult isOpaquePointerType(IWildcardType var1) {
      if (var1 != null && var1.isResolved()) {
         String var2 = var1.getNativeType().getName();
         if (var2.equals("HANDLE") || var2.equals("HGLOBAL") || var2.equals("HLOCAL")) {
            return ChainedOperationResult.TRUE_STOP;
         }
      }

      return ChainedOperationResult.FALSE_CONTINUE;
   }
}
