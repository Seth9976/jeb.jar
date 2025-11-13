package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class bkf implements IEventListener {
   bkf(bke var1, com.pnfsoftware.jeb.corei.parsers.dex.bK var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.q) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            this.RF.io.clear();
         }
      }
   }
}
