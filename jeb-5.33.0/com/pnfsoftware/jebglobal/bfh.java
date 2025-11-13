package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bfh implements IEventListener {
   bfh(bfg var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange) {
         this.pC.Aj.clear();
         if (var2.getData() instanceof UnitChangeEventData && ((UnitChangeEventData)var2.getData()).type == 7) {
            this.pC.pC();
         }

         this.pC.notifyListeners(var2);
      }
   }
}
