package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum CodelessLibraryID {
   OpenSSL("OpenSSL", "openssl"),
   libCURL("cURL", "libcurl"),
   libSSH2("libssh2"),
   zlib("zlib"),
   jsoncpp("JsonCpp"),
   bzip2("bzip2"),
   poco("POCO");

   private final String name;
   private final String typelibName;

   private CodelessLibraryID(String var3) {
      this(var3, var3);
   }

   private CodelessLibraryID(String var3, String var4) {
      this.name = var3;
      this.typelibName = var4;
   }

   public String getName() {
      return this.name;
   }

   public String getTypelibName() {
      return this.typelibName;
   }
}
