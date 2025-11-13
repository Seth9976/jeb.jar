package com.pnfsoftware.jeb.corei.parsers.apk;

public enum KD {
   pC("android.permission-group.CALENDAR"),
   A("android.permission-group.CAMERA"),
   kS("android.permission-group.CONTACTS"),
   wS("android.permission-group.LOCATION"),
   UT("android.permission-group.MICROPHONE"),
   E("android.permission-group.PHONE"),
   sY("android.permission-group.SENSORS"),
   ys("android.permission-group.SMS"),
   ld("android.permission-group.STORAGE");

   private final String gp;

   private KD(String var3) {
      this.gp = var3;
   }

   public String pC() {
      return this.gp;
   }
}
