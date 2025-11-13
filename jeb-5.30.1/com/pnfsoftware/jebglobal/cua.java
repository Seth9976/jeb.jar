package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cua implements adl {
   public static final cua q = new cua("unknown");
   public static final cua RF = new cua("...");
   @SerId(1)
   private final String xK;

   public cua(String var1) {
      this.xK = var1;
   }

   @Override
   public String q() {
      return this.xK;
   }
}
