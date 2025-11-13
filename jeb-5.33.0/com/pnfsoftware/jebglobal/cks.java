package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class cks implements IEventListener {
   cks(ckr var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitCreated && var1.getData() instanceof IBinaryUnit) {
         IBinaryUnit var2 = (IBinaryUnit)var1.getData();
         if (var2.getParent() instanceof IArtifact) {
            ckr.pC.debug("Top-level binary unit was created: %s", var2);

            try {
               this.pC.pC(var2);
            } catch (Exception var4) {
               ckr.pC.catching(var4);
            }
         }
      }
   }
}
