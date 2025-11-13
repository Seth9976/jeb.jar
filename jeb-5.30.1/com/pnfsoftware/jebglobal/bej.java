package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bej implements IEventListener {
   bej(bei var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (bei.q && var1.getType() == J.UnitProcessed && var1.getSource() instanceof IELFUnit) {
         IELFUnit var5 = (IELFUnit)var1.getSource();
         bek var6 = this.q.q(var5);
         if (var6 != null) {
            bei.oW.info(S.L("%s: Dart AOT snapshot found"), UnitUtil.buildFullyQualifiedUnitPath(var5));
            if (this.q.q(var5, var6)) {
               INativeCodeUnit var7 = (INativeCodeUnit)UnitUtil.findFirstChildByType(var5, INativeCodeUnit.class, false);
               if (var7 != null) {
                  this.q.q(var6, var7, false);
               }
            }
         }
      } else if (var1.getType() == J.CodeAnalysisCompleted && var1.getSource() instanceof INativeCodeUnit) {
         INativeCodeUnit var2 = (INativeCodeUnit)var1.getSource();
         if (var2.getParent() instanceof IELFUnit) {
            IELFUnit var3 = (IELFUnit)var2.getParent();
            bek var4 = this.q.q(var3);
            if (var4 != null) {
               bei.oW.info(S.L("%s: Dart AOT snapshot found"), UnitUtil.buildFullyQualifiedUnitPath(var3));
               if (this.q.q(var3, var4)) {
                  this.q.q(var4, var2, true);
               }
            }
         }
      }
   }
}
