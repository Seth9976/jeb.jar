package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;

class cvc implements IEventListener {
   cvc(cvb var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitProcessed && var1.getSource() instanceof IApkUnit) {
         IApkUnit var2 = (IApkUnit)var1.getSource();
         if (!UnitUtil.filterDescendants(var2, -1, new cvd(this)).isEmpty()) {
            String var3 = "";
            var3 = var3
               + S.L(
                  "The APK may have been protected (some strings or other static items may have been removed from Dex, and could be restored by native code)\n"
               );
            if (this.q.q()) {
               var3 = var3 + Strings.ff(S.L("Try running the plugin \"%s\" and see if the protected data can be recovered"), cvb.RF.getName());
            }

            cvb.q.info(var3);
         }
      }
   }
}
