package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.format.Strings;

public class AdbPackage {
   private String name;
   private String path;

   public AdbPackage(String var1, String var2) {
      this.name = var1;
      this.path = var2;
   }

   public String getName() {
      return this.name;
   }

   public String getPath() {
      return this.path;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", this.getName(), this.getPath());
   }
}
