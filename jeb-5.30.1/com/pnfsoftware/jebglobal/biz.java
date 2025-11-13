package com.pnfsoftware.jebglobal;

public enum biz {
   q,
   RF,
   xK,
   Dw,
   Uv,
   oW,
   gO;

   private final String nf = "." + this.name().replace("__", "-").replace('_', ' ').toLowerCase();

   public String q() {
      return this.nf;
   }
}
