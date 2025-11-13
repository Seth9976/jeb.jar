package com.pnfsoftware.jeb.corei.parsers.llvmbc;

import com.pnfsoftware.jeb.util.base.OSType;

// $VF: synthetic class
class nI {
   static {
      try {
         q[OSType.WIN64.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         q[OSType.LINUX64.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         q[OSType.OSX64.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
