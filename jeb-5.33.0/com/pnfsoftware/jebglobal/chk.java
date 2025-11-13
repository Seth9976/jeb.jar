package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.util.format.Strings;

public class chk extends JebRuntimeException {
   private static final long serialVersionUID = 1L;
   int pC;

   public chk(String var1, int var2) {
      super(var1);
      this.pC = var2;
   }

   @Override
   public String getMessage() {
      return Strings.ff("%Xh: %s", this.pC, super.getMessage());
   }
}
