package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

public enum Mh {
   pC,
   A,
   kS,
   wS,
   UT;

   public boolean pC() {
      return this == UT || this == wS;
   }
}
