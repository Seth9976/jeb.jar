package com.pnfsoftware.jeb.core.units.code.android.adb;

public enum AdbForwardType {
   TCP("tcp"),
   LOCAL_ABSTRACT("localabstract"),
   LOCAL_RESERVED("localreserved"),
   LOCAL("localfilesystem"),
   DEVICE("dev"),
   JDWP("jdwp");

   private final String paramName;

   private AdbForwardType(String var3) {
      this.paramName = var3;
   }

   @Override
   public String toString() {
      return this.paramName;
   }
}
