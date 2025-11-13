package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.android.adb.AdbDevice;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbDeviceStatus;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapperFactory;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetEnumerator;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GK implements IDebuggerTargetEnumerator {
   private static final ILogger pC = GlobalLog.getLogger(GK.class);
   private AdbWrapperFactory A;

   public GK() {
      try {
         this.A = new AdbWrapperFactory();
      } catch (IOException var2) {
         pC.catchingSilent(var2);
      }
   }

   AdbWrapperFactory pC() {
      return this.A;
   }

   @Override
   public List listMachines() {
      ArrayList var1 = new ArrayList();
      if (this.A != null) {
         List var2 = this.A.listDevices();
         if (var2 != null) {
            for (AdbDevice var4 : var2) {
               String var5 = var4.getSerial();
               String var6 = "";
               byte var7 = 0;
               if (var4.getStatus() == AdbDeviceStatus.ONLINE) {
                  var7 |= 1;
               }

               String var8 = null;
               String var9 = var4.getModel();
               if (var9 != null) {
                  var8 = var9.replace('_', ' ');
               }

               var1.add(new vi(this, var5, var6, var7, var8));
            }
         }
      }

      return var1;
   }
}
