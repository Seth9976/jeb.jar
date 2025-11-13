package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import java.util.Arrays;
import java.util.List;

public class cbj extends AbstractDOptimizer {
   public cbj() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bto.Dw(this);
   }

   @Override
   public int perform() {
      if (!bws.xK(this.g)) {
         return 0;
      } else {
         String var1 = JvmMethodSig.parse(this.ctx.getMethodSignature()).csig;
         List var2 = Arrays.asList(this.ctx);
         caz var3 = new caz(this.g, var1, var2);
         var3.q(false);
         var3.RF(true);
         var3.xK(true);
         var3.Dw(false);
         return var3.Uv();
      }
   }
}
