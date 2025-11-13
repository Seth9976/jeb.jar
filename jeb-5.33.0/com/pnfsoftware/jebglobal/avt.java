package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;

public class avt implements IUnmangledData {
   private final String pC;
   private final boolean A;

   public avt(String var1) {
      this(var1, false);
   }

   public avt(String var1, boolean var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String getFull() {
      return this.pC;
   }

   @Override
   public String getSimple() {
      return this.getFull();
   }

   @Override
   public boolean isRawData() {
      return this.A;
   }
}
