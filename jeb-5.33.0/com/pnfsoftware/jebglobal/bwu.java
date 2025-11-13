package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import java.util.Arrays;
import java.util.List;

public class bwu extends AbstractDOptimizer {
   public bwu() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      if (!bsg.kS(this.g)) {
         return 0;
      } else {
         String var1 = JvmMethodSig.parse(this.ctx.getMethodSignature()).csig;
         List var2 = Arrays.asList(this.ctx);
         bwk var3 = new bwk(this.g, var1, var2);
         var3.pC(false);
         var3.A(true);
         var3.kS(true);
         var3.wS(false);
         return var3.pC();
      }
   }
}
