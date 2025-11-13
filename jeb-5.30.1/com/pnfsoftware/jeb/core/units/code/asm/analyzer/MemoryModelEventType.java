package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

public enum MemoryModelEventType {
   GENERAL_UPDATE,
   ITEM_ADDED,
   ITEM_REMOVED,
   LABEL_UPDATE,
   COMMENT_UPDATE,
   REFERENCE_UPDATE;

   public boolean isDeepChange() {
      return this == GENERAL_UPDATE || this == ITEM_ADDED || this == ITEM_REMOVED;
   }
}
