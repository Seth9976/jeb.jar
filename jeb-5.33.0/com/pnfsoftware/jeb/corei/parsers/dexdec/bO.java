package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jebglobal.bic;

class bO implements IEventListener {
   bO(Ws var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange && var2.getData() instanceof UnitChangeEventData) {
         UnitChangeEventData var3 = (UnitChangeEventData)var2.getData();
         bic var4 = this.pC.A();
         if (var3.type == 7) {
            if (var4 != null) {
               var4.E().wS();
               var4.sY().A();
               var4.UT().A();
            }
         } else if (var3.type == 8 && var4 != null && var3.target instanceof IDexItem) {
            String var5 = ((IDexItem)var3.target).getAddress(false);
            this.pC.removeDecompilation(var5);
         }
      }
   }
}
