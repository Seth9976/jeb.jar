package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class Ro extends ey {
   public Ro() {
      super(32);
   }

   public p q(BytesBlock var1) throws ProcessorException {
      byte[] var2 = var1.getBECode();
      Je var3 = vf.q(var2);
      if (var3 == null || var3.RF(var2)) {
         throw new ProcessorException(getUndefinedMessage(var2));
      } else if (var3.xK(var2)) {
         throw new ProcessorException(getUnpredictableMessage(var2, var3.q(var2, null).q()));
      } else {
         return this.q(var1, var3);
      }
   }
}
