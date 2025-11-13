package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;

public class asv extends CodeDocument {
   IERoutineContext pC;
   boolean A = true;
   boolean kS = true;
   boolean wS = true;

   public asv(IERoutineContext var1) {
      this.pC = var1;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      asw var5 = new asw(0L, this);
      var5.registerAnchor("single_anchor");
      var5.pC(this.pC);
      return var5;
   }
}
