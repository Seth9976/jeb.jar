package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;

// $VF: synthetic class
class bha {
   static {
      try {
         pC[ContextAccessType.NONE_SAFE.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         pC[ContextAccessType.NONE.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         pC[ContextAccessType.READ_ONLY.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         pC[ContextAccessType.WRITE_ONLY.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[ContextAccessType.READ_WRITE.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[ContextAccessType.UNKNOWN.ordinal()] = 6;
      } catch (NoSuchFieldError var0) {
      }
   }
}
