package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class ckm implements IEventListener {
   ckm(ckl var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getType() == J.UnitProcessed && var1.getSource() instanceof IApkUnit) {
         IApkUnit var2 = (IApkUnit)var1.getSource();
         if (!var2.getLibrariesForArch(AndroidPlatformABI.ARM64).isEmpty()) {
            ckl.pC
               .info(
                  S.L("%s: Native libraries found, consider running the plugin: \"%s\""),
                  UnitUtil.buildFullyQualifiedUnitPath(var2),
                  this.pC.getPluginInformation().getName()
               );
         }
      }
   }
}
