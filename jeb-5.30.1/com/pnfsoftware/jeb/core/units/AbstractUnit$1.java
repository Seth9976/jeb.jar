package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class AbstractUnit$1 implements IEventListener {
   AbstractUnit$1(AbstractUnit var1) {
      this.this$0 = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1 instanceof JebEvent && var1.getType() == J.PropertyChange && var1.getData() instanceof PropertyChangeNotification) {
         PropertyChangeNotification var2 = (PropertyChangeNotification)var1.getData();

         for (PropertyChangeNotification.Entry var4 : var2.entries()) {
            this.this$0.onPropertyChange(var4.getPropertyFullyQualifiedName());
         }
      }
   }
}
