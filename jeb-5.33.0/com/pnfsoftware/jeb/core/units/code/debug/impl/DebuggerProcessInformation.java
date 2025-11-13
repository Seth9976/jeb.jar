package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerProcessInformation;
import com.pnfsoftware.jeb.util.format.Strings;

public class DebuggerProcessInformation implements IDebuggerProcessInformation {
   private long id;
   private String name;
   private int flags;

   public DebuggerProcessInformation(long var1, String var3, int var4) {
      this.id = var1;
      this.name = var3;
      this.flags = var4;
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
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,name=%s,flags=%Xh", this.getId(), this.getName(), this.getFlags());
   }
}
