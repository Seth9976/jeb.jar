package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;

class HE extends AbstractTransientUnitRepresentation {
   HE(sy var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.pC = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return new RC(this.pC);
   }
}
