package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;

public class bav {
   INativeDataItem q;
   int RF;
   int xK;
   boolean Dw;

   bav(INativeDataItem var1, int var2) {
      this(var1, var2, 0);
   }

   bav(INativeDataItem var1, int var2, int var3) {
      this(var1, var2, var3, false);
   }

   bav(INativeDataItem var1, int var2, int var3, boolean var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }
}
