package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;

class tl extends AbstractTransientUnitRepresentation {
   tl(bK var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return this.q.getDisassemblyDocument();
   }
}
