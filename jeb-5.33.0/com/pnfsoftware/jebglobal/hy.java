package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class hy extends FH {
   public hy(int var1, int var2, Yg var3, Integer var4) {
      super(var1, var2 | 1024, var3, pC(var4));
   }

   @Override
   public Yg[] pC() {
      return new Yg[]{this.E()[0]};
   }

   private static Yg pC(Integer var0) {
      return Yg.pC((long)var0.intValue(), 524288);
   }
}
