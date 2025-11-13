package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;

public class axx {
   INativeDataItem pC;
   int A;
   int kS;
   boolean wS;

   axx(INativeDataItem var1, int var2) {
      this(var1, var2, 0);
   }

   axx(INativeDataItem var1, int var2, int var3) {
      this(var1, var2, var3, false);
   }

   axx(INativeDataItem var1, int var2, int var3, boolean var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }
}
