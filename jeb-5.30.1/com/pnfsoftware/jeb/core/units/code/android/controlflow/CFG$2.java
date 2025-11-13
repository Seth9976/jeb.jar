package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Iterator;

class CFG$2 implements Iterable {
   CFG$2(CFG var1, long var2) {
      this.this$0 = var1;
      this.val$fromAddress = var2;
   }

   @Override
   public Iterator iterator() {
      Couple var1 = this.this$0.getInstructionLocation(this.val$fromAddress);
      return var1 == null
         ? this.this$0.new InstructionsIterator(null, 0)
         : this.this$0.new InstructionsIterator((BasicBlock)var1.getFirst(), (Integer)var1.getSecond());
   }
}
