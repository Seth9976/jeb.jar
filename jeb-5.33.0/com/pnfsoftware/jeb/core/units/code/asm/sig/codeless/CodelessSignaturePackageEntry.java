package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Ws;
import java.io.File;

public class CodelessSignaturePackageEntry {
   private final File file;
   private final ExecutableModelMetadata metadata;

   public CodelessSignaturePackageEntry(File var1) {
      this.file = var1;
      this.metadata = Ws.pC(var1);
   }

   public File getFile() {
      return this.file;
   }

   public ExecutableModelMetadata getMetadata() {
      return this.metadata;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.file == null ? 0 : this.file.hashCode());
      return 31 * var1 + (this.metadata == null ? 0 : this.metadata.hashCode());
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
         CodelessSignaturePackageEntry var2 = (CodelessSignaturePackageEntry)var1;
         if (this.file == null) {
            if (var2.file != null) {
               return false;
            }
         } else if (!this.file.equals(var2.file)) {
            return false;
         }

         if (this.metadata == null) {
            if (var2.metadata != null) {
               return false;
            }
         } else if (!this.metadata.equals(var2.metadata)) {
            return false;
         }

         return true;
      }
   }
}
