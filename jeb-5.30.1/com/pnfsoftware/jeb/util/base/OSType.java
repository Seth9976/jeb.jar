package com.pnfsoftware.jeb.util.base;

public enum OSType {
   UNKNOWN(null),
   LINUX32("lin32"),
   OSX32("osx32"),
   WIN32("win32"),
   LINUX64("lin64"),
   OSX64("osx64"),
   WIN64("win64"),
   LINUX_ARM64("lin_arm64"),
   OSX_ARM64("osx_arm64"),
   WIN_ARM64("win_arm64");

   private final String foldername;

   private OSType(String var3) {
      this.foldername = var3;
   }

   public String getFolderName() {
      return this.foldername;
   }

   public static OSType determine() {
      String var0 = System.getProperty("os.arch", "");
      String var1 = System.getProperty("os.name", "");
      boolean var2 = var0.contains("64");
      boolean var3 = var0.contains("aarch") || var0.contains("arm");
      if (var1.startsWith("Windows")) {
         if (var2) {
            return var3 ? WIN_ARM64 : WIN64;
         } else {
            return WIN32;
         }
      } else if (var1.startsWith("Mac")) {
         if (var2) {
            return var3 ? OSX_ARM64 : OSX64;
         } else {
            return OSX32;
         }
      } else if (var1.startsWith("Linux")) {
         if (var2) {
            return var3 ? LINUX_ARM64 : LINUX64;
         } else {
            return LINUX32;
         }
      } else {
         return UNKNOWN;
      }
   }

   public boolean isKnown() {
      return this != UNKNOWN;
   }

   public boolean isWindows() {
      return this == WIN32 || this == WIN64 || this == WIN_ARM64;
   }

   public boolean isMac() {
      return this == OSX32 || this == OSX64 || this == OSX_ARM64;
   }

   public boolean isLinux() {
      return this == LINUX32 || this == LINUX64 || this == LINUX_ARM64;
   }

   public boolean is32bit() {
      return this == WIN32 || this == OSX32 || this == LINUX32;
   }

   public boolean is64bit() {
      return this == WIN64 || this == OSX64 || this == LINUX64 || this == WIN_ARM64 || this == OSX_ARM64 || this == LINUX_ARM64;
   }
}
