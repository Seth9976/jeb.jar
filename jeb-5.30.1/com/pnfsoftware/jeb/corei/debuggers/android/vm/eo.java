package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerBreakpoint;
import com.pnfsoftware.jebglobal.Nv;

public class eo extends AbstractDebuggerBreakpoint {
   com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   CI RF;
   Nv xK;
   boolean Dw;

   public eo(CI var1, com.pnfsoftware.jeb.corei.parsers.dex.bK var2, String var3, Nv var4, int var5) {
      super(var3, new UnitAddress(var2, var3));
      this.q = var2;
      this.RF = var1;
      this.xK = var4;
      this.flags = var5;
      this.Dw = true;
   }

   @Override
   public String getAddress() {
      String var1 = super.getAddress();
      if (this.q != null) {
         String var2 = this.q.xK().q(var1, true);
         if (var2 != null) {
            return var2;
         }
      }

      return var1;
   }

   CI q() {
      return this.RF;
   }

   Nv RF() {
      return this.xK;
   }

   @Override
   public boolean setEnabled(boolean var1) {
      if (!this.RF.q(this, var1)) {
         return false;
      } else {
         this.Dw = var1;
         return true;
      }
   }

   @Override
   public boolean isEnabled() {
      return this.Dw;
   }
}
