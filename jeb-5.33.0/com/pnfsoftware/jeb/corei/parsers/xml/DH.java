package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.StaticTextDocument;

class DH extends AbstractTransientUnitRepresentation {
   DH(cq var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.pC = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return (IGenericDocument)(this.pC.A != null ? new Sv(this.pC) : new StaticTextDocument(this.pC.UT));
   }
}
