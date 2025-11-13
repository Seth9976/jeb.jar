package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class vh {
   @SerId(1)
   int pC;

   public vh(int x) {
      this.pC = x;
   }

   public int x() {
      return this.pC;
   }
}
