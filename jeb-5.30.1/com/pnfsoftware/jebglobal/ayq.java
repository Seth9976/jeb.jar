package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;

public class ayq implements IUnmangledData {
   private final String q;
   private final boolean RF;

   public ayq(String var1) {
      this(var1, false);
   }

   public ayq(String var1, boolean var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String getFull() {
      return this.q;
   }

   @Override
   public String getSimple() {
      return this.getFull();
   }

   @Override
   public boolean isRawData() {
      return this.RF;
   }
}
