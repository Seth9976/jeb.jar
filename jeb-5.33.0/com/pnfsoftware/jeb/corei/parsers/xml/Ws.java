package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class Ws implements IEventListener {
   Ws(Sv var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1 instanceof JebEvent && var1.getType() == J.PropertyChange && var1.getData() instanceof PropertyChangeNotification) {
         PropertyChangeNotification var2 = (PropertyChangeNotification)var1.getData();
         Object[] var10000 = new Object[]{var2};
         this.pC.pC(true);
      }
   }
}
