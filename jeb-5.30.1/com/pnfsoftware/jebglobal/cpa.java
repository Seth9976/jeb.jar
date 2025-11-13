package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class cpa {
   int q;
   List RF = new ArrayList();

   cpa(int var1) {
      this.q = var1;
   }

   @Override
   public String toString() {
      return this.RF.isEmpty() ? "Stream:{empty}" : Strings.ff("Stream:{size=%X,pages=%s}", this.q, Formatter.formatHexNumbers(this.RF));
   }
}
