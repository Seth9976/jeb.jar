package com.pnfsoftware.jeb.core.units.code.android.adb;

public enum AdbDeviceStatus {
   UNKNOWN,
   OFFLINE,
   BOOTLOADER,
   RECOVERY,
   ONLINE;

   public static AdbDeviceStatus fromString(String var0) {
      if (var0 == null) {
         return UNKNOWN;
      } else {
         String var1 = var0.trim().toLowerCase();
         switch (var1) {
            case "offline":
               return OFFLINE;
            case "bootloader":
               return BOOTLOADER;
            case "recovery":
               return RECOVERY;
            case "online":
            case "device":
               return ONLINE;
            default:
               return UNKNOWN;
         }
      }
   }
}
