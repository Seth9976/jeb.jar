package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;

// $VF: synthetic class
class anp {
   static {
      try {
         pC[StorageEntry.Type.REGISTER.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         pC[StorageEntry.Type.REGISTER_PAIR.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         pC[StorageEntry.Type.STACK.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
