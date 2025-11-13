package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;

class blu extends AbstractTransientUnitRepresentation {
   blu(bls var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return this.q.getSourceDocument();
   }
}
