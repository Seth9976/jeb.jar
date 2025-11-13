package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class bt implements Callable {
   bt(fG var1, com.pnfsoftware.jeb.corei.parsers.elf.vb var2, long var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public Boolean q() throws Exception {
      this.xK.Uv.q(true);
      long var1 = this.xK.Uv.q(this.q);
      if (var1 != 0L) {
         long var3 = var1 + this.RF;
         String var5 = this.xK.q(var3);
         return this.xK.q(var5, 0, null, true) != null;
      } else {
         return false;
      }
   }
}
