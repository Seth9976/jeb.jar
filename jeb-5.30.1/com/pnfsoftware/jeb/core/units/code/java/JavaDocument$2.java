package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class JavaDocument$2 implements IEventListener {
   JavaDocument$2(JavaDocument var1) {
      this.this$0 = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1 instanceof JebEvent && var1.getType() == J.PropertyChange && var1.getData() instanceof PropertyChangeNotification) {
         PropertyChangeNotification var2 = (PropertyChangeNotification)var1.getData();
         Object[] var10000 = new Object[]{var2};
         this.this$0.setupOptions(true);
      }
   }
}
