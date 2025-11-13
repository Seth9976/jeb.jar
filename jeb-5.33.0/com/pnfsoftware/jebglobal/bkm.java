package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bkm {
   @SerId(1)
   private bkl pC;

   public bkm() {
      this.pC = bkl.pC;
   }
}
