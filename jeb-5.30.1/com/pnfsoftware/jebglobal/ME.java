package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

public interface ME {
   ME.eo q();

   boolean RF();

   @Ser
   public static enum eo {
      q,
      RF,
      xK,
      Dw;
   }
}
