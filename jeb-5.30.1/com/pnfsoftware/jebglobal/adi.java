package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum adi {
   q,
   RF,
   xK,
   Dw;

   public boolean q() {
      return this == q || this == xK;
   }

   public boolean RF() {
      return this == RF || this == Dw;
   }
}
