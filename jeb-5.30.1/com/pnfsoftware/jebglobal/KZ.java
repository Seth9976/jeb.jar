package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class KZ implements IEventListener {
   KZ(zJ var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitCreated && var1.getData() instanceof IUnit) {
         IUnit var2 = (IUnit)var1.getData();
         if (this.q.If != null) {
            for (IUnitContribution var4 : this.q.If.xK) {
               if (var4.isTarget(var2)) {
                  IUnitContribution var5 = (IUnitContribution)Jh.RF(var4.getClass());
                  if (var5 != null) {
                     var2.getContributions().add(var5);
                  }
               }
            }
         }
      }
   }
}
