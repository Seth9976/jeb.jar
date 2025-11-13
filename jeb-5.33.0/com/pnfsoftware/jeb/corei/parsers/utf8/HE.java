package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.HexDumpDocument;

class HE extends AbstractTransientUnitRepresentation {
   HE(rQ var1, String var2, boolean var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      return new HexDumpDocument(new BytesInput(new byte[]{0, 0, 0, 0}));
   }
}
