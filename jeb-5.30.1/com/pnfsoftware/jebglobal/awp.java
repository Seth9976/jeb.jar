package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awp extends avx {
   private static final StructuredLogger JY = aeg.q(awp.class);

   public awp() {
      super(avy.za);
   }

   @Override
   protected boolean Dw() {
      IEVar var1 = this.nf.getVariableByName("SP0");
      if (var1 == null) {
         Object[] var18 = new Object[0];
         return true;
      } else {
         apm var2 = new apm(this.nf);
         var2.q(var1);
         int var3 = var2.RF(var1);
         Object[] var10000 = new Object[]{var3};
         var3 = var2.q();
         var10000 = new Object[]{var3};
         int var4 = 0;

         try {
            JY.beginSection("Performing dead-code removal");
            arm var5 = (arm)this.za.getOptimizerObject(arm.class);
            if (var5 != null) {
               var4 += var5.performOnTarget(this.nf);
            }
         } finally {
            JY.closeSection();
         }

         try {
            JY.beginSection("Performing variable propagation");
            atk var15 = (atk)this.za.getOptimizerObject(atk.class);
            if (var15 != null) {
               var4 += var15.performOnTarget(this.nf);
            }
         } finally {
            JY.closeSection();
         }

         var10000 = new Object[]{var4};
         return true;
      }
   }
}
