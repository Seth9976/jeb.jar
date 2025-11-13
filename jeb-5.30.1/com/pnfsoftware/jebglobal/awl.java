package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awl extends avx {
   private static final StructuredLogger JY = aeg.q(awl.class);

   public awl() {
      super(avy.oW);
   }

   @Override
   protected boolean Dw() {
      int var1 = 0;

      try {
         JY.beginSection("Performing EGroupElt resolution before SSA (to achieve better SSA)");
         asb var2 = (asb)this.za.getOptimizerObject(asb.class);
         var1 += var2.performOnTarget(this.nf);
      } finally {
         JY.closeSection();
      }

      Object[] var10000 = new Object[]{var1};
      int var10 = this.nf.getCfg().getInstructionCount();
      this.q(JY, "Pre-SSA optimization run");
      int var3 = this.nf.getCfg().getInstructionCount();
      var10000 = new Object[]{var10, var3};
      var10000 = new Object[0];
      apa var4 = new apa(this.nf);
      int var5 = var4.q();
      var10000 = new Object[]{var5};
      this.nf.getCfg().setDFADefaultCollectionFlags(1);
      var10000 = new Object[]{var5};
      if (this.nf.getPrototype() == null) {
         var10000 = new Object[0];
      } else {
         JY.iH("Applying prototype: %s", this.nf.getPrototype());
         IEPrototypeHandler var6 = this.Uv.getPrototypeHandler(this.nf);
         var6.applyKnownPrototype(true);
         var10000 = new Object[0];
      }

      return true;
   }
}
