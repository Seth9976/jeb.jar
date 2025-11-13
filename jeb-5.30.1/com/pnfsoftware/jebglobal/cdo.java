package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;

public class cdo extends AbstractDOptimizer {
   static String q = cvm.q(
      new byte[]{
         100,
         115,
         23,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         96,
         45,
         8,
         15,
         6,
         23,
         79,
         114,
         0,
         0,
         96,
         101,
         38,
         11,
         23,
         23,
         78,
         67,
         13,
         15,
         9,
         72,
         96,
         45,
         8,
         15,
         6,
         23,
         79
      },
      1,
      76
   );

   public cdo() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
      this.addTag("deobfuscator");
      bto.Dw(this);
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else if (!bws.RF(this.g)) {
         return 0;
      } else {
         cdp var1 = new cdp(this, this.ctx);
         return var1.q();
      }
   }
}
