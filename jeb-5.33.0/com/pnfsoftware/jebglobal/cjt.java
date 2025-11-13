package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cjt implements abt {
   @SerId(1)
   private final long pC;

   public cjt(long var1) {
      this.pC = var1;
   }

   @Override
   public String pC() {
      if (this.pC == -1L) {
         return "EXCEPTION_CONTINUE_EXECUTION";
      } else if (this.pC == 0L) {
         return "EXCEPTION_CONTINUE_SEARCH";
      } else {
         return this.pC == 1L ? "EXCEPTION_EXECUTE_HANDLER" : Strings.ff("%Xh", this.pC);
      }
   }
}
