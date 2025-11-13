package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.util.format.Strings;

public class coo extends JebRuntimeException {
   private static final long serialVersionUID = 1L;
   int q;

   public coo(String var1, int var2) {
      super(var1);
      this.q = var2;
   }

   public coo(String var1, int var2, Throwable var3) {
      super(var1, var3);
      this.q = var2;
   }

   public int q() {
      return this.q;
   }

   @Override
   public String getMessage() {
      return Strings.ff("%Xh: %s", this.q, super.getMessage());
   }
}
