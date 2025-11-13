package com.pnfsoftware.jeb.core.units.code;

public enum DecompilerOutputType {
   OTHER(".txt"),
   JAVA(".java"),
   C(".c"),
   CPP(".cpp"),
   PYTHON(".py");

   private final String ext;

   private DecompilerOutputType(String var3) {
      this.ext = var3;
   }

   public String getPreferredExtension() {
      return this.ext;
   }
}
