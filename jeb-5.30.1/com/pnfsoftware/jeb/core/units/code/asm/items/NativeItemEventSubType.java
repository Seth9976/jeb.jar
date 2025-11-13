package com.pnfsoftware.jeb.core.units.code.asm.items;

public enum NativeItemEventSubType {
   RENAMED,
   ATTRIBUTE_SET,
   ATTRIBUTE_REMOVED,
   STRUCT_FIELD_ADDED,
   STRUCT_FIELD_REMOVED,
   STRUCT_FIELD_RENAMED,
   ROUTINE_SET,
   PROTOTYPE_UPDATED;

   public boolean isAttributeRelated() {
      return this == ATTRIBUTE_REMOVED || this == ATTRIBUTE_SET;
   }
}
