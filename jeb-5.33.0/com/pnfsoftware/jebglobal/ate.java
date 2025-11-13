package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.OSType;

// $VF: synthetic class
class ate {
   static {
      try {
         pC[OSType.WIN64.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         pC[OSType.WIN_ARM64.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         pC[OSType.OSX64.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         pC[OSType.OSX_ARM64.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[OSType.LINUX64.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[OSType.LINUX_ARM64.ordinal()] = 6;
      } catch (NoSuchFieldError var0) {
      }
   }
}
