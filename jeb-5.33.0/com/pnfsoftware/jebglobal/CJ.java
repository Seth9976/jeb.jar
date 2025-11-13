package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class CJ extends ch {
   public CJ() {
      super(64);
   }

   public jR pC(BytesBlock var1) throws ProcessorException {
      byte[] var2 = var1.getBECode();
      tz var3 = lj.pC(var2);
      if (var3 == null || var3.A(var2)) {
         throw new ProcessorException(getUndefinedMessage(var2));
      } else if (var3.kS(var2)) {
         throw new ProcessorException(getUnpredictableMessage(var2, var3.pC(var2, null).pC()));
      } else {
         return this.pC(var1, var3);
      }
   }
}
