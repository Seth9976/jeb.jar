package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.format.Strings;

public class AdbDeviceAndProcess {
   private AdbDevice device;
   private AdbProcess process;

   public AdbDeviceAndProcess(AdbDevice var1, AdbProcess var2) {
      this.device = var1;
      this.process = var2;
   }

   public AdbDevice getDevice() {
      return this.device;
   }

   public AdbProcess getProcess() {
      return this.process;
   }

   @Override
   public String toString() {
      return Strings.ff("%s/%d(%s)", this.device.getSerial(), this.process.getPid(), this.process.getName());
   }
}
