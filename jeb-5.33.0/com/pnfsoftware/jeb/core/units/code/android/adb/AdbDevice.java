package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.format.Strings;

public class AdbDevice {
   private String serial;
   public AdbDeviceStatus status;
   private String name;
   private String model;
   private String device;

   public AdbDevice(String var1, AdbDeviceStatus var2) {
      this(var1, var2, null, null, null);
   }

   public AdbDevice(String var1, AdbDeviceStatus var2, String var3, String var4, String var5) {
      if (Strings.isBlank(var1)) {
         throw new IllegalArgumentException("Illegal device serial: " + var1);
      } else if (var2 == null) {
         throw new IllegalArgumentException("Illegal device status: " + var2);
      } else {
         this.serial = var1;
         this.status = var2;
         this.name = var3;
         this.model = var4;
         this.device = var5;
      }
   }

   public String getSerial() {
      return this.serial;
   }

   public AdbDeviceStatus getStatus() {
      return this.status;
   }

   public String getName() {
      return this.name;
   }

   public String getModel() {
      return this.model;
   }

   public String getDevice() {
      return this.device;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", this.serial, this.status.toString().toLowerCase());
   }
}
