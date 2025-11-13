package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class chw {
   int pC;
   List A = new ArrayList();

   chw(int var1) {
      this.pC = var1;
   }

   @Override
   public String toString() {
      return this.A.isEmpty() ? "Stream:{empty}" : Strings.ff("Stream:{size=%X,pages=%s}", this.pC, Formatter.formatHexNumbers(this.A));
   }
}
