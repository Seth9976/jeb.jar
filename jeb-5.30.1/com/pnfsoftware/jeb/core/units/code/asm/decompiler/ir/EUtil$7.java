package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jebglobal.aoe;

class EUtil$7 extends aoe {
   EUtil$7(IERoutineContext var1, IEStatement var2, int var3) {
      super(var1);
      this.val$stm0 = var2;
      this.val$minSize = var3;
   }

   @Override
   public IEStatement[] processStatement(IEStatement var1, int var2) {
      if (var1 == this.val$stm0) {
         var1.setSize(this.val$minSize);
      }

      return new IEStatement[]{var1};
   }
}
