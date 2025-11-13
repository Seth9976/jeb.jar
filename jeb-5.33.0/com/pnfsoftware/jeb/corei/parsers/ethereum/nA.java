package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class nA extends eW {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;

   public nA(int var1, int var2, int var3) {
      super(var1);
      this.pC = var2;
      this.A = var3;
   }

   public int kS() {
      return this.pC;
   }

   public int wS() {
      return this.A;
   }

   @Override
   public String toString() {
      return Strings.ff("PRV:%s(in=%d,out=%d)", super.toString(), this.pC, this.A);
   }
}
