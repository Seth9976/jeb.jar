package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;

public class byy extends AbstractDOptimizer {
   static String pC = ckx.pC(
      new byte[]{
         81,
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
      121
   );

   public byy() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else if (!bsg.A(this.g)) {
         return 0;
      } else {
         byz var1 = new byz(this, this.ctx);
         return var1.pC();
      }
   }
}
