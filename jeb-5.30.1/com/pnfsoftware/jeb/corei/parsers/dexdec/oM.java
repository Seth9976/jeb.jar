package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jebglobal.blz;

class oM implements IEventListener {
   oM(ej var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange && var2.getData() instanceof UnitChangeEventData) {
         UnitChangeEventData var3 = (UnitChangeEventData)var2.getData();
         blz var4 = this.q.RF();
         if (var3.type == 7) {
            if (var4 != null) {
               var4.gO().gO();
               var4.nf().Uv();
               var4.oW().Uv();
            }
         } else if (var3.type == 8 && var4 != null && var3.target instanceof IDexItem) {
            String var5 = ((IDexItem)var3.target).getAddress(false);
            this.q.removeDecompilation(var5);
         }
      }
   }
}
