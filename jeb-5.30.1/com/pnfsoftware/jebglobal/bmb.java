package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bmb {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private bmc xK;

   public bmb(int var1, int var2, bmc var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public bmc xK() {
      return this.xK;
   }
}
