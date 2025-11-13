package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bhw implements IEventListener {
   bhw(bhv var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.pC.wS) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            this.pC.E();
            this.pC.notifyListeners(var2);
         }
      }
   }
}
