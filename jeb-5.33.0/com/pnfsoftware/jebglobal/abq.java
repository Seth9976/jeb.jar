package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum abq {
   pC,
   A,
   kS,
   wS;

   public boolean pC() {
      return this == A || this == wS;
   }
}
