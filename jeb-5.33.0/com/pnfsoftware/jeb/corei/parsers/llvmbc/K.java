package com.pnfsoftware.jeb.corei.parsers.llvmbc;

import com.pnfsoftware.jeb.util.base.OSType;

// $VF: synthetic class
class K {
   static {
      try {
         pC[OSType.WIN64.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[OSType.LINUX64.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[OSType.OSX64.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
