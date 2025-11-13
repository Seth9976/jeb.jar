package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class cuy implements IEventListener {
   cuy(cux var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitProcessed && var1.getSource() instanceof IApkUnit) {
         IApkUnit var2 = (IApkUnit)var1.getSource();
         if (var2.createGenericUnpacker() != null && var2.createGenericUnpacker().shouldAttemptUnpack()) {
            String var3 = S.L("The APK may have been packed.");
            if (this.q.q()) {
               var3 = var3 + " " + S.L("You may run the generic unpacker to see if additional dex files can be recovered.");
            }

            cux.q.warn(var3);
         }
      }
   }
}
