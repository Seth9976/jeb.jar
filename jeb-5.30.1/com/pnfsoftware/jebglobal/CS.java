package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerModule;
import com.pnfsoftware.jeb.util.format.Strings;

public class CS extends AbstractDebuggerModule {
   private long q;

   public CS(String var1, long var2, long var4) {
      super(-1L, var1, var2);
      this.q = var4;
   }

   public long q() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("%s @ %Xh (DT: %Xh)", this.getName(), this.getBaseAddress(), this.q());
   }
}
