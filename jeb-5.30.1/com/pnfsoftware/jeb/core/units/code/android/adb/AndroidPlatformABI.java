package com.pnfsoftware.jeb.core.units.code.android.adb;

public enum AndroidPlatformABI {
   ARM("armeabi", true),
   ARM7("armeabi-v7a", false),
   ARM64("arm64-v8a", false),
   MIPS("mips", true),
   MIPS64("mips64", true),
   X86("x86", false),
   X64("x86_64", false);

   private final String abiName;
   private final boolean depr;

   private AndroidPlatformABI(String var3, boolean var4) {
      this.abiName = var3;
      this.depr = var4;
   }

   public boolean isIntel() {
      return this == X86 || this == X64;
   }

   public boolean isARM() {
      return this == ARM || this == ARM7 || this == ARM64;
   }

   public boolean isMIPS() {
      return this == MIPS || this == MIPS64;
   }

   public boolean isDeprecated() {
      return this.depr;
   }

   @Override
   public String toString() {
      return this.abiName;
   }

   public static AndroidPlatformABI fromName(String var0) {
      for (AndroidPlatformABI var4 : values()) {
         if (var4.toString().equals(var0)) {
            return var4;
         }
      }

      return null;
   }
}
