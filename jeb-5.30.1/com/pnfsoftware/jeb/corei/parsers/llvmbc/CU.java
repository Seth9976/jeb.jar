package com.pnfsoftware.jeb.corei.parsers.llvmbc;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.StaticTextDocument;

class CU extends AbstractTransientUnitRepresentation {
   CU(eo var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return new StaticTextDocument(this.q.RF);
   }
}
