package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jebglobal.hH;
import java.util.Comparator;

class HE implements Comparator {
   HE(sy var1) {
      this.pC = var1;
   }

   public int pC(hH var1, hH var2) {
      return var1.E - var2.E;
   }
}
