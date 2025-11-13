package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ExecutableModelMetadata {
   @SerId(1)
   private final CodelessLibraryID libraryId;
   @SerId(2)
   private final CodelessLibraryVersion libraryVersion;
   @SerId(3)
   private final int modelVersion;
   @SerId(4)
   private int uuid;

   public ExecutableModelMetadata(CodelessLibraryID var1, CodelessLibraryVersion var2, int var3) {
      this.libraryId = var1;
      this.libraryVersion = var2;
      this.modelVersion = var3;
   }

   public CodelessLibraryID getLibraryId() {
      return this.libraryId;
   }

   public CodelessLibraryVersion getLibraryVersion() {
      return this.libraryVersion;
   }

   public int getModelVersion() {
      return this.modelVersion;
   }

   @Override
   public String toString() {
      return "ExecutableModelMetadata [libraryId="
         + this.libraryId
         + ", libraryVersion="
         + this.libraryVersion
         + ", modelVersion="
         + this.modelVersion
         + ", uuid="
         + this.uuid
         + "]";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.libraryId == null ? 0 : this.libraryId.hashCode());
      var1 = 31 * var1 + (this.libraryVersion == null ? 0 : this.libraryVersion.hashCode());
      var1 = 31 * var1 + this.modelVersion;
      return 31 * var1 + this.uuid;
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
         ExecutableModelMetadata var2 = (ExecutableModelMetadata)var1;
         if (this.libraryId != var2.libraryId) {
            return false;
         } else {
            if (this.libraryVersion == null) {
               if (var2.libraryVersion != null) {
                  return false;
               }
            } else if (!this.libraryVersion.equals(var2.libraryVersion)) {
               return false;
            }

            return this.modelVersion != var2.modelVersion ? false : this.uuid == var2.uuid;
         }
      }
   }
}
