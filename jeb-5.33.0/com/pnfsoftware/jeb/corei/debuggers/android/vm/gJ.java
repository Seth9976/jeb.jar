package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class gJ implements IEventListener {
   gJ(Tb var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1 instanceof JebEvent) {
         J var2 = ((JebEvent)var1).getType();
         if (var1.getData() instanceof com.pnfsoftware.jeb.corei.parsers.dex.vi) {
            com.pnfsoftware.jeb.corei.parsers.dex.vi var3 = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1.getData();
            if (var2 == J.UnitCreated) {
               this.pC.registerDebuggee(var3);
            } else if (var2 == J.UnitDestroyed) {
               this.pC.unregisterDebuggee(var3);
            }
         }
      }
   }
}
