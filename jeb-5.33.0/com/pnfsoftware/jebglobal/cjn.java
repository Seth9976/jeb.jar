package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cjn implements abt {
   public static final cjn pC = new cjn("unknown");
   public static final cjn A = new cjn("...");
   @SerId(1)
   private final String kS;

   public cjn(String var1) {
      this.kS = var1;
   }

   @Override
   public String pC() {
      return this.kS;
   }
}
