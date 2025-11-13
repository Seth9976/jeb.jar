package com.pnfsoftware.jeb.core.units.code.asm.items;

public enum NativeItemEventType {
   UPDATED,
   MODIFIED,
   DISPOSED;

   public boolean isDeepChange() {
      return this == DISPOSED || this == MODIFIED;
   }
}
