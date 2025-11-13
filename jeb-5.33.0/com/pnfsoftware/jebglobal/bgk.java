package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bgk implements IEventListener {
   bgk(bgj var1, com.pnfsoftware.jeb.corei.parsers.dex.vi var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.pC) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            this.A.vP.clear();
         }
      }
   }
}
