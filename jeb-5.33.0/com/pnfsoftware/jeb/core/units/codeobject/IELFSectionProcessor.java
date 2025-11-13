package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import java.nio.ByteBuffer;

public interface IELFSectionProcessor {
   ChainedOperationResult process(IELFUnit var1, IELFSectionEntry var2, ByteBuffer var3);

   public static class Result {
      public String appendedDescription;
      public String appendedMetadata;

      public Result(String var1, String var2) {
         this.appendedDescription = var1;
         this.appendedMetadata = var2;
      }
   }
}
