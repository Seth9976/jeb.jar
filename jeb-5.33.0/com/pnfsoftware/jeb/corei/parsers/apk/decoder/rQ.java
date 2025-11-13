package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.StaticTextDocument;
import com.pnfsoftware.jeb.util.base.Throwables;

class rQ extends AbstractTransientUnitRepresentation {
   rQ(DH var1, long var2, String var4, boolean var5) {
      super(var2, var4, var5);
      this.pC = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      try {
         String var1 = this.pC.A();
         return new StaticTextDocument(var1);
      } catch (Exception var2) {
         return new StaticTextDocument("A parsing error occurred:\n" + Throwables.formatStacktrace(var2));
      }
   }
}
