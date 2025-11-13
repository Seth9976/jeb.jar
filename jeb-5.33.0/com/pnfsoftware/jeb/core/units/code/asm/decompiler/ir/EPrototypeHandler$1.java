package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jebglobal.alx;
import java.util.Map;

class EPrototypeHandler$1 extends alx {
   EPrototypeHandler$1(EPrototypeHandler var1, IERoutineContext var2, Map var3) {
      super(var2);
      this.this$0 = var1;
      this.val$records = var3;
   }

   @Override
   public IEStatement[] processStatement(IEStatement var1, int var2) {
      IEStatement var3 = (IEStatement)this.val$records.get(var2);
      return var3 != null ? new IEStatement[]{var3, var1} : new IEStatement[]{var1};
   }
}
