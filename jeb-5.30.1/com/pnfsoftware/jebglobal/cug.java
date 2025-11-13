package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cug implements adl {
   public static final long q = -1L;
   public static final long RF = 0L;
   public static final long xK = 1L;
   @SerId(1)
   private final long Dw;

   public cug(long var1) {
      this.Dw = var1;
   }

   @Override
   public String q() {
      if (this.Dw == -1L) {
         return "EXCEPTION_CONTINUE_EXECUTION";
      } else if (this.Dw == 0L) {
         return "EXCEPTION_CONTINUE_SEARCH";
      } else {
         return this.Dw == 1L ? "EXCEPTION_EXECUTE_HANDLER" : Strings.ff("%Xh", this.Dw);
      }
   }
}
