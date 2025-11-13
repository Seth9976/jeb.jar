package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jebglobal.aoe;
import java.util.Set;

class EUtil$8 extends aoe {
   EUtil$8(IERoutineContext var1, Set var2, int var3) {
      super(var1);
      this.val$filteredSet = var2;
      this.val$minSize = var3;
   }

   @Override
   public IEStatement[] processStatement(IEStatement var1, int var2) {
      if (this.val$filteredSet.contains(var1)) {
         var1.setSize(this.val$minSize);
      }

      return new IEStatement[]{var1};
   }
}
