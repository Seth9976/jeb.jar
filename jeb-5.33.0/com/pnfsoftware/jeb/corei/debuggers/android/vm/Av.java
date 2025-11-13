package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerBreakpoint;
import com.pnfsoftware.jebglobal.PZ;

public class Av extends AbstractDebuggerBreakpoint {
   com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   Tb A;
   PZ kS;
   boolean wS;

   public Av(Tb var1, com.pnfsoftware.jeb.corei.parsers.dex.vi var2, String var3, PZ var4, int var5) {
      super(var3, new UnitAddress(var2, var3));
      this.pC = var2;
      this.A = var1;
      this.kS = var4;
      this.flags = var5;
      this.wS = true;
   }

   @Override
   public String getAddress() {
      String var1 = super.getAddress();
      if (this.pC != null) {
         String var2 = this.pC.A().pC(var1, true);
         if (var2 != null) {
            return var2;
         }
      }

      return var1;
   }

   PZ pC() {
      return this.kS;
   }

   @Override
   public boolean setEnabled(boolean var1) {
      if (!this.A.pC(this, var1)) {
         return false;
      } else {
         this.wS = var1;
         return true;
      }
   }

   @Override
   public boolean isEnabled() {
      return this.wS;
   }
}
