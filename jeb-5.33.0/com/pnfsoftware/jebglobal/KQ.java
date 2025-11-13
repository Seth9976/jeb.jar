package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import java.nio.ByteBuffer;

public class KQ implements IELFSectionProcessor {
   @Override
   public ChainedOperationResult process(IELFUnit var1, IELFSectionEntry var2, ByteBuffer var3) {
      if (var2.getType() != 1879048195) {
         return ChainedOperationResult.ignore();
      } else {
         Fy var4 = Fy.pC(var3, var1.isELF64(), var2.getSize());
         var1.setData("ARM_Attributes", var4, true);
         return new ChainedOperationResult(
            new IELFSectionProcessor.Result(null, "- ARM specific attributes:\n  " + var4), ChainedOperationResult.ContinuationStatus.STOP
         );
      }
   }
}
