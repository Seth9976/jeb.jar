package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.android.adb.AdbDevice;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbProcess;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerMachineInformation;
import java.util.ArrayList;
import java.util.List;

public class vi extends DebuggerMachineInformation {
   GK pC;

   public vi(GK var1, String var2, String var3, int var4, String var5) {
      super(var2, var3, var4, var5);
      this.pC = var1;
   }

   @Override
   public List getProcesses() {
      ArrayList var1 = new ArrayList();
      AdbDevice var2 = this.pC(this.getName());
      if (var2 != null) {
         AdbWrapper var3 = this.pC.pC().createWrapper(var2.getSerial());
         List var4 = var3.listProcesses();
         if (var4 != null) {
            for (AdbProcess var6 : var4) {
               int var7 = var6.getPid();
               String var8 = var6.getName();
               byte var9 = 0;
               var1.add(new p(var7, var8, var9));
            }
         }
      }

      return var1;
   }

   private AdbDevice pC(String var1) {
      List var2 = this.pC.pC().listDevices();
      if (var2 != null) {
         for (AdbDevice var4 : var2) {
            if (var4.getSerial().equals(var1)) {
               return var4;
            }
         }
      }

      return null;
   }
}
