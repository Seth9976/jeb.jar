package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

class acx extends AbstractTransientUnitRepresentation {
   acx(acu var1, long var2, String var4, boolean var5, IERoutineContext var6) {
      super(var2, var4, var5);
      this.A = var1;
      this.pC = var6;
   }

   @Override
   public IGenericDocument createDocument() {
      return new asv(this.pC);
   }
}
