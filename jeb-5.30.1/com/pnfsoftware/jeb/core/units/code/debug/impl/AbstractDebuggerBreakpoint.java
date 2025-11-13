package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class AbstractDebuggerBreakpoint implements IDebuggerBreakpoint {
   protected String dbgAddress;
   protected UnitAddress ua;
   protected int flags;

   public AbstractDebuggerBreakpoint(String var1, UnitAddress var2) {
      this.dbgAddress = var1;
      this.ua = var2;
   }

   @Override
   public String getAddress() {
      return this.dbgAddress;
   }

   protected void setAddress(String var1) {
      this.dbgAddress = var1;
   }

   @Override
   public UnitAddress getUnitAddress() {
      return this.ua;
   }

   protected void setUnitAddress(UnitAddress var1) {
      this.ua = var1;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String toString() {
      return Strings.ff("%s (%s) [enabled=%b]", this.getAddress(), this.getUnitAddress(), this.isEnabled());
   }
}
