package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;

@SerDisabled
public class TypeLibraryEntry implements Comparable {
   File file;
   TypeLibraryMetadata hdr;
   ITypeLibrary typelib;
   int status;

   TypeLibraryEntry(File var1, TypeLibraryMetadata var2) {
      this.file = var1;
      this.hdr = var2;
   }

   public File getFile() {
      return this.file;
   }

   public TypeLibraryMetadata getMetadataHeader() {
      return this.hdr;
   }

   public ITypeLibrary getTypelib() {
      return this.typelib;
   }

   public boolean isLoaded() {
      return this.typelib != null;
   }

   @Override
   public String toString() {
      return Strings.ff("Loaded:%b|Header=%s|File:%s", this.getTypelib() != null, this.getMetadataHeader(), this.getFile());
   }

   public int compareTo(TypeLibraryEntry var1) {
      if (this.hdr == null) {
         return var1 != null && var1.hdr != null ? 1 : 0;
      } else if (var1 != null && var1.hdr != null) {
         ProcessorType var2 = this.hdr.getPrimaryProcessorType();
         ProcessorType var3 = var1.hdr.getPrimaryProcessorType();
         if (var2 == null) {
            return 1;
         } else if (var3 == null) {
            return -1;
         } else {
            int var4 = Integer.compare(var2.id(), var3.id());
            if (var4 != 0) {
               return var4;
            } else {
               SubsystemType var5 = this.hdr.getPrimarySubsystemType();
               SubsystemType var6 = var1.hdr.getPrimarySubsystemType();
               if (var5 == null) {
                  return 1;
               } else if (var6 == null) {
                  return -1;
               } else {
                  var4 = Integer.compare(var5.id(), var6.id());
                  if (var4 != 0) {
                     return var4;
                  } else {
                     int var7 = this.hdr.getGroupId();
                     int var8 = var1.hdr.getGroupId();
                     var4 = Integer.compare(var7, var8);
                     if (var4 != 0) {
                        return var4;
                     } else {
                        var4 = Double.compare(this.hdr.getPriorityOrder(), var1.hdr.getPriorityOrder());
                        return var4 != 0 ? var4 : 0;
                     }
                  }
               }
            }
         }
      } else {
         return -1;
      }
   }
}
