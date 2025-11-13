package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public class bta {
   public int pC;
   public bsz[] A;

   @Override
   public String toString() {
      return Strings.ff("O%d{%s}", this.pC, Arrays.toString((Object[])this.A));
   }
}
