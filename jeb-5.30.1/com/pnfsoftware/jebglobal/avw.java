package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.OSType;

// $VF: synthetic class
class avw {
   static {
      try {
         q[OSType.WIN64.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         q[OSType.WIN_ARM64.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         q[OSType.OSX64.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         q[OSType.OSX_ARM64.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         q[OSType.LINUX64.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }

      try {
         q[OSType.LINUX_ARM64.ordinal()] = 6;
      } catch (NoSuchFieldError var0) {
      }
   }
}
