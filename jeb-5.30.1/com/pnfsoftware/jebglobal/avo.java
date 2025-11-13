package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

public class avo extends CodeDocument {
   IERoutineContext q;
   boolean RF = true;
   boolean xK = true;
   boolean Dw = true;

   public avo(IERoutineContext var1) {
      this.q = var1;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      avp var5 = new avp(0L, this);
      var5.registerAnchor("single_anchor");
      var5.q(this.q);
      return var5;
   }
}
