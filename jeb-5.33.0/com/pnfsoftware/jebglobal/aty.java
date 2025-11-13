package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aty extends atf {
   private static final StructuredLogger WR = aco.pC(aty.class);

   public aty() {
      super(atg.WR);
   }

   @Override
   protected boolean kS() {
      Object[] var10000 = new Object[0];
      amq var1 = new amq(this.ys);
      int var2 = var1.pC();
      if (var2 > 0) {
         this.pC(WR, "Performing opt. pass after successfully identifying prologue/epilogue instructions: " + var2);
      }

      boolean var3 = EUtil.expandCalls(this.ys, false);
      var10000 = new Object[]{var3};
      var10000 = new Object[0];
      var2 = this.UT.insertReturns(this.ys);
      var10000 = new Object[]{var2};
      var2 = this.UT.defaultPCConversion(this.ys);
      var10000 = new Object[]{var2};
      return true;
   }
}
