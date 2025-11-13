package com.pnfsoftware.jeb.core.units.code.asm.type;

import java.util.Comparator;

class TypeLibraryService$1 implements Comparator {
   TypeLibraryService$1(TypeLibraryService var1) {
      this.this$0 = var1;
   }

   public int compare(TypeLibraryEntry var1, TypeLibraryEntry var2) {
      return Double.compare(var1.getMetadataHeader().getPriorityOrder(), var2.getMetadataHeader().getPriorityOrder());
   }
}
