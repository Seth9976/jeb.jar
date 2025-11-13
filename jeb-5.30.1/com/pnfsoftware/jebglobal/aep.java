package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

class aep extends AbstractTransientUnitRepresentation {
   aep(aem var1, long var2, String var4, boolean var5, IERoutineContext var6) {
      super(var2, var4, var5);
      this.RF = var1;
      this.q = var6;
   }

   @Override
   public IGenericDocument createDocument() {
      return new avo(this.q);
   }
}
