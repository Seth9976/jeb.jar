package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

public interface aap {
   aap.eo q();

   boolean RF();

   boolean xK();

   @Ser
   public static enum eo {
      q,
      RF,
      xK;
   }
}
