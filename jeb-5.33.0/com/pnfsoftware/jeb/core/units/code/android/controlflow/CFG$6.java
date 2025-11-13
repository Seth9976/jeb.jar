package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import java.util.Comparator;

class CFG$6 implements Comparator {
   CFG$6(CFG var1) {
      this.this$0 = var1;
   }

   public int compare(BasicBlock var1, BasicBlock var2) {
      return Long.compare(((ILocatedInstruction)var1.insns.get(0)).getOffset(), ((ILocatedInstruction)var2.insns.get(0)).getOffset());
   }
}
