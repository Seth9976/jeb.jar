package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class K implements IEventListener {
   K(Sv var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange) {
         this.pC.notifyListeners(var2);
      }
   }
}
