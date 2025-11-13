package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atr extends atf {
   private static final StructuredLogger WR = aco.pC(atr.class);

   public atr() {
      super(atg.E);
   }

   @Override
   protected boolean kS() {
      int var1 = 0;

      try {
         WR.beginSection("Performing EGroupElt resolution before SSA (to achieve better SSA)");
         apn var2 = (apn)this.gp.getOptimizerObject(apn.class);
         var1 += var2.performOnTarget(this.ys);
      } finally {
         WR.closeSection();
      }

      Object[] var10000 = new Object[]{var1};
      int var10 = this.ys.getCfg().getInstructionCount();
      this.pC(WR, "Pre-SSA optimization run");
      int var3 = this.ys.getCfg().getInstructionCount();
      var10000 = new Object[]{var10, var3};
      var10000 = new Object[0];
      amt var4 = new amt(this.ys);
      var1 = var4.pC();
      var10000 = new Object[]{var1};
      this.ys.getCfg().setDFADefaultCollectionFlags(1);
      if (this.ys.getPrototype() == null) {
         var10000 = new Object[0];
      } else {
         WR.iH("Applying prototype: %s", this.ys.getPrototype());
         IEPrototypeHandler var5 = this.UT.getPrototypeHandler(this.ys);
         var5.applyKnownPrototype(true);
         var10000 = new Object[0];
      }

      return true;
   }
}
