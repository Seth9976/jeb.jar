package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.AsciiDocument;

class qt extends AbstractTransientUnitRepresentation {
   qt(rQ var1, String var2, boolean var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return new AsciiDocument(this.pC.getInput());
   }
}
