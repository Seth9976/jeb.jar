package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CodelessLibraryVersion {
   public static final CodelessLibraryVersion UNK = new CodelessLibraryVersion("unknown");
   @SerId(1)
   private final String strVersion;

   public CodelessLibraryVersion(String var1) {
      this.strVersion = var1;
   }

   public String getStringVersion() {
      return this.strVersion;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.strVersion == null ? 0 : this.strVersion.hashCode());
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
         CodelessLibraryVersion var2 = (CodelessLibraryVersion)var1;
         if (this.strVersion == null) {
            if (var2.strVersion != null) {
               return false;
            }
         } else if (!this.strVersion.equals(var2.strVersion)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.strVersion;
   }
}
