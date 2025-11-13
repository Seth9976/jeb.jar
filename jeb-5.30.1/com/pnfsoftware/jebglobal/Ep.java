package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;

class Ep extends AbstractTransientUnitRepresentation {
   Ep(um var1, long var2, String var4, boolean var5, QK var6) {
      super(var2, var4, var5);
      this.RF = var1;
      this.q = var6;
   }

   @Override
   public IGenericDocument createDocument() {
      return this.q;
   }
}
