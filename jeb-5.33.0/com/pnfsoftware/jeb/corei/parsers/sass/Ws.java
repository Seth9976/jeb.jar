package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class Ws implements IEventListener {
   Ws(K var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      ICodeObjectUnit var2 = null;
      if (var1.getType() == J.UnitProcessed && var1.getSource() instanceof ICodeObjectUnit var3) {
         var2 = var3;
      } else if (var1.getType() == J.CodeAnalysisCompleted
         && var1.getSource() instanceof INativeCodeUnit var4
         && var4.getParent() instanceof ICodeObjectUnit var5) {
         var2 = var5;
      }

      if (var2 != null) {
         int var7 = this.pC.pC(var2);
         if (var7 > 0) {
            K.pC.info(S.L("%s: CUDA fatbin detected"), UnitUtil.buildFullyQualifiedUnitPath(var2));
         }
      }
   }
}
