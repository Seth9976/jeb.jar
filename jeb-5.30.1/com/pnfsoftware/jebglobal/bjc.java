package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bjc implements IEventListener {
   bjc(bjb var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange) {
         this.q.tW.clear();
         if (var2.getData() instanceof UnitChangeEventData && ((UnitChangeEventData)var2.getData()).type == 7) {
            this.q.q();
         }

         this.q.notifyListeners(var2);
      }
   }
}
