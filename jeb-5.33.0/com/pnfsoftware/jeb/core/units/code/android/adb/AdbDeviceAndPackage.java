package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.format.Strings;

public class AdbDeviceAndPackage {
   private AdbDevice device;
   private AdbPackage pkg;

   public AdbDeviceAndPackage(AdbDevice var1, AdbPackage var2) {
      this.device = var1;
      this.pkg = var2;
   }

   public AdbDevice getDevice() {
      return this.device;
   }

   public AdbPackage getPackage() {
      return this.pkg;
   }

   @Override
   public String toString() {
      return Strings.ff("%s/%s", this.device.getSerial(), this.pkg.getName());
   }
}
