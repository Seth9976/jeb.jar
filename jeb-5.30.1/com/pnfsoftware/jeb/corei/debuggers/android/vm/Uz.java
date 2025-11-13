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

public class Uz implements IDebuggerTargetEnumerator {
   private static final ILogger q = GlobalLog.getLogger(Uz.class);
   private AdbWrapperFactory RF;

   public Uz() {
      try {
         this.RF = new AdbWrapperFactory();
      } catch (IOException var2) {
         q.catchingSilent(var2);
      }
   }

   public Uz(AdbWrapperFactory var1) {
      this.RF = var1;
   }

   AdbWrapperFactory q() {
      return this.RF;
   }

   @Override
   public List listMachines() {
      ArrayList var1 = new ArrayList();
      if (this.RF != null) {
         List var2 = this.RF.listDevices();
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

               var1.add(new tl(this, var5, var6, var7, var8));
            }
         }
      }

      return var1;
   }
}
