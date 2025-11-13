package com.pnfsoftware.jeb.util.net;

import java.io.File;

public class FormFileEntry {
   File file;
   String nameOverride;

   public FormFileEntry(File var1) {
      this(var1, null);
   }

   public FormFileEntry(File var1, String var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.file = var1;
         this.nameOverride = var2;
      }
   }

   public File getFile() {
      return this.file;
   }

   public String getName() {
      return this.nameOverride != null ? this.nameOverride : this.file.getName();
   }
}
