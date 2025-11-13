package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerBreakpoint;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class xI extends AbstractDebuggerBreakpoint {
   private um Dw;
   boolean q;
   long RF;
   int xK;

   xI(um var1, long var2, int var4) {
      this(var1, var2, var4, Strings.ff("%Xh", var2));
   }

   private xI(um var1, long var2, int var4, String var5) {
      super(var5, new UnitAddress(DebuggerUtil.getPotentialDebuggee(var1, var2), var5));
      this.RF = var2;
      this.xK = var4;
      this.Dw = var1;
      this.q = false;
   }

   public long q() {
      return this.RF;
   }

   public void q(int var1) {
      this.xK = var1;
   }

   public int RF() {
      return this.xK;
   }

   @Override
   public boolean setEnabled(boolean var1) {
      if (var1 == this.q) {
         return false;
      } else {
         return var1 ? this.Dw.q(this.dbgAddress, this.xK, null, true) != null : this.Dw.q(this, true);
      }
   }

   @Override
   public boolean isEnabled() {
      return this.q;
   }
}
