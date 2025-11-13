package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.AbstractTransientUnitRepresentation;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.impl.Line;
import com.pnfsoftware.jeb.core.output.text.impl.StaticTextDocument;
import java.util.ArrayList;

class Bu extends AbstractTransientUnitRepresentation {
   Bu(tw var1, String var2, boolean var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public IGenericDocument createDocument() {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < 1000; var2++) {
         var1.add(new Line(var2 + " this line was auto-generated"));
      }

      return new StaticTextDocument(var1);
   }
}
