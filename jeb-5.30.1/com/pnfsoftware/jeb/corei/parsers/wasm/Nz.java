package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Nz {
   @SerId(1)
   int q;
   @SerId(2)
   String RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;

   public Nz(int var1, String var2, int var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d%s,payload:%Xh(%Xh)", this.q, this.RF == null ? "" : "('" + this.RF + "')", this.xK, this.Dw);
   }
}
