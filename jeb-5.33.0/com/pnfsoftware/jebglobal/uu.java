package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerModule;
import com.pnfsoftware.jeb.util.format.Strings;

public class uu extends AbstractDebuggerModule {
   private long pC;

   public uu(String var1, long var2, long var4) {
      super(-1L, var1, var2);
      this.pC = var4;
   }

   public long pC() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("%s @ %Xh (DT: %Xh)", this.getName(), this.getBaseAddress(), this.pC());
   }
}
