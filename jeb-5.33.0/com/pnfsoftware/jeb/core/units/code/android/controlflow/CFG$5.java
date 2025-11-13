package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import java.util.Comparator;

class CFG$5 implements Comparator {
   CFG$5(CFG var1) {
      this.this$0 = var1;
   }

   public int compare(ILocatedInstruction var1, ILocatedInstruction var2) {
      return Long.compare(var1.getOffset(), var2.getOffset());
   }
}
