package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;

class PY extends AbstractTransientUnitRepresentation {
   PY(tw var1, String var2, boolean var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return new Nt(this.q);
   }
}
