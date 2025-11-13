package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class AbstractDebuggerThread implements IDebuggerThread {
   public long id;
   public String name;

   public AbstractDebuggerThread(long var1, String var3) {
      this.id = var1;
      this.name = var3;
   }

   @Override
   public long getId() {
      return this.id;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d,name:%s", this.getId(), this.getName());
   }
}
