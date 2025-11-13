package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ModuleId {
   private static final String UNKNOWN_MODULE_NAME = "UNKNOWN";
   public static final ModuleId UNKNOWN_MODULE_ID = new ModuleId("UNKNOWN");
   @SerId(1)
   private final String fileName;

   public ModuleId(String var1) {
      this.fileName = var1;
   }

   public boolean isUnknown() {
      return this.fileName.equals("UNKNOWN");
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.fileName == null ? 0 : this.fileName.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ModuleId var2 = (ModuleId)var1;
         if (this.fileName == null) {
            if (var2.fileName != null) {
               return false;
            }
         } else if (!this.fileName.equals(var2.fileName)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "ModuleId [fileName=" + this.fileName + "]";
   }

   public String getFileName() {
      return this.fileName;
   }
}
