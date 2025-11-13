package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class OW implements IEventListener {
   OW(Nj var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitCreated && var1.getData() instanceof IUnit) {
         IUnit var2 = (IUnit)var1.getData();
         if (this.pC.WR != null) {
            for (IUnitContribution var4 : this.pC.WR.kS) {
               if (var4.isTarget(var2)) {
                  IUnitContribution var5 = (IUnitContribution)MG.A(var4.getClass());
                  if (var5 != null) {
                     var2.getContributions().add(var5);
                  }
               }
            }
         }
      }
   }
}
