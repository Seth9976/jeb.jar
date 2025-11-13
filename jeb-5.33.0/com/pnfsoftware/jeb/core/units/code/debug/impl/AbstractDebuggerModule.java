package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerModule;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class AbstractDebuggerModule implements IDebuggerModule {
   long id;
   String name;
   long base;

   public AbstractDebuggerModule(long var1, String var3, long var4) {
      this.id = var1;
      this.name = var3;
      this.base = var4;
   }

   @Override
   public long getId() {
      return this.id;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public long getBaseAddress() {
      return this.base;
   }

   @Override
   public String toString() {
      return Strings.ff("%d:%s@%Xh", this.getId(), this.getName(), this.getBaseAddress());
   }
}
