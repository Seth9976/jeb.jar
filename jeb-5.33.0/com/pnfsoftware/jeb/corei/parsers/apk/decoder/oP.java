package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jebglobal.Bz;
import java.util.Comparator;

class oP implements Comparator {
   oP(sy var1) {
      this.pC = var1;
   }

   public int pC(Bz var1, Bz var2) {
      return Bz.kS.indexOf(var1.pC) - Bz.kS.indexOf(var2.pC);
   }
}
