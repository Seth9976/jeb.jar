package com.pnfsoftware.jeb.client.jebio;

import com.pnfsoftware.jeb.client.S;

public enum SampleDetermination {
   UNKNOWN(0, S.L("Unknown")),
   CLEAN(1, S.L("Clean (the file is legitimate and non-malicious)")),
   SUSPICIOUS(10, S.L("Not sure (the file is suspicious)")),
   MALWARE(20, S.L("Malicious (the file is clearly malicious)"));

   private final int level;
   private final String message;

   private SampleDetermination(int var3, String var4) {
      this.level = var3;
      this.message = var4;
   }

   public int getLevel() {
      return this.level;
   }

   public String getMessage() {
      return this.message;
   }

   public static SampleDetermination fromLevel(int var0) {
      for (SampleDetermination var4 : values()) {
         if (var4.getLevel() == var0) {
            return var4;
         }
      }

      return null;
   }
}
