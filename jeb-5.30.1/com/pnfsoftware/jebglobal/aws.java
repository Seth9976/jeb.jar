package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aws extends avx {
   private static final StructuredLogger JY = aeg.q(aws.class);

   public aws() {
      super(avy.JY);
   }

   @Override
   protected boolean Dw() {
      Object[] var10000 = new Object[0];
      aox var1 = new aox(this.nf);
      int var2 = var1.q();
      if (var2 > 0) {
         this.q(JY, "Performing opt. pass after successfully identifying prologue/epilogue instructions: " + var2);
      }

      boolean var3 = EUtil.expandCalls(this.nf, false);
      var10000 = new Object[]{var3};
      var10000 = new Object[0];
      var2 = this.Uv.insertReturns(this.nf);
      var10000 = new Object[]{var2};
      var2 = this.Uv.defaultPCConversion(this.nf);
      var10000 = new Object[]{var2};
      return true;
   }
}
