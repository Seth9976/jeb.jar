package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jebglobal.alx;

class EUtil$9 extends alx {
   EUtil$9(IERoutineContext var1, boolean var2) {
      super(var1);
      this.val$alsoExpandPCAssign = var2;
   }

   @Override
   public IEStatement[] processStatement(IEStatement var1, int var2) {
      if (var1.getSize() == 1 && (var1 instanceof IECall || this.val$alsoExpandPCAssign && EUtil.isPCAssign(var1))) {
         var1.adjustSize(1);
      }

      return new IEStatement[]{var1};
   }
}
