package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;

public class oM extends CodeDocumentPart {
   public oM(long var1) {
      super(var1);
   }

   public void q(String var1) {
      this.appendAndRecord(var1, ItemClassIdentifiers.MARKUP_ELEMENT);
   }
}
