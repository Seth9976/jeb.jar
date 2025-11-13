package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerBreakpoint;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class xa extends AbstractDebuggerBreakpoint {
   private ia wS;
   boolean pC;
   long A;
   int kS;

   xa(ia var1, long var2, int var4) {
      this(var1, var2, var4, Strings.ff("%Xh", var2));
   }

   private xa(ia var1, long var2, int var4, String var5) {
      super(var5, new UnitAddress(DebuggerUtil.getPotentialDebuggee(var1, var2), var5));
      this.A = var2;
      this.kS = var4;
      this.wS = var1;
      this.pC = false;
   }

   public void pC(int var1) {
      this.kS = var1;
   }

   @Override
   public boolean setEnabled(boolean var1) {
      if (var1 == this.pC) {
         return false;
      } else {
         return var1 ? this.wS.pC(this.dbgAddress, this.kS, null, true) != null : this.wS.pC(this, true);
      }
   }

   @Override
   public boolean isEnabled() {
      return this.pC;
   }
}
