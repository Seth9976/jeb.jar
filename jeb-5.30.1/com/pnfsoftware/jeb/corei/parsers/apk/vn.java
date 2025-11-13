package com.pnfsoftware.jeb.corei.parsers.apk;

public enum vn {
   q("android.permission-group.CALENDAR"),
   RF("android.permission-group.CAMERA"),
   xK("android.permission-group.CONTACTS"),
   Dw("android.permission-group.LOCATION"),
   Uv("android.permission-group.MICROPHONE"),
   oW("android.permission-group.PHONE"),
   gO("android.permission-group.SENSORS"),
   nf("android.permission-group.SMS"),
   gP("android.permission-group.STORAGE");

   private final String za;

   private vn(String var3) {
      this.za = var3;
   }

   public String q() {
      return this.za;
   }

   public String RF() {
      int var1 = this.za.lastIndexOf(46);
      return var1 < 0 ? this.za : this.za.substring(var1 + 1);
   }

   public static vn q(String var0) {
      for (vn var4 : values()) {
         if (var4.za.equals(var0)) {
            return var4;
         }
      }

      vn var6 = null;

      for (vn var5 : values()) {
         if (var5.za.contains(var0)) {
            if (var6 != null) {
               return null;
            }

            var6 = var5;
         }
      }

      return var6;
   }
}
