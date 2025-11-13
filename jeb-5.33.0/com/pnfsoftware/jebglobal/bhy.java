package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.StaticTextDocument;
import com.pnfsoftware.jeb.util.format.Strings;

class bhy extends AbstractTransientUnitRepresentation {
   bhy(bhv var1, long var2, String var4, boolean var5, String var6) {
      super(var2, var4, var5);
      this.A = var1;
      this.pC = var6;
   }

   @Override
   public IGenericDocument createDocument() {
      String var1 = Strings.ff(S.L("Final IR (Intermediate Representation) for method: %s\n\n%s"), this.A.E, this.pC);
      return new StaticTextDocument(var1);
   }
}
