package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Fw extends qx {
   @SerId(1)
   int Dw;
   @SerId(2)
   int Uv;

   public Fw(int var1, int var2, int var3) {
      super(var1);
      this.Dw = var2;
      this.Uv = var3;
   }

   @Override
   public int xK() {
      return 1;
   }

   public int Uv() {
      return this.Dw;
   }

   public int oW() {
      return this.Uv;
   }

   @Override
   public String toString() {
      return Strings.ff("PRV:%s(in=%d,out=%d)", super.toString(), this.Dw, this.Uv);
   }
}
