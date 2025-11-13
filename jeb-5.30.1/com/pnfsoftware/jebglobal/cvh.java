package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class cvh implements IEventListener {
   cvh(cvg var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitCreated && var1.getData() instanceof IBinaryUnit) {
         IBinaryUnit var2 = (IBinaryUnit)var1.getData();
         if (var2.getParent() instanceof IArtifact) {
            cvg.q.debug("Top-level binary unit was created: %s", var2);

            try {
               this.q.q(var2);
            } catch (Exception var4) {
               cvg.q.catching(var4);
            }
         }
      }
   }
}
