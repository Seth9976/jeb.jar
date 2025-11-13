package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class blt implements IEventListener {
   blt(bls var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.q.oW) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            this.q.gO();
            this.q.notifyListeners(var2);
         }
      }
   }
}
