package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;

public class bzh extends AbstractDOptimizer {
   private int pC = 1;

   public bzh() {
      this.addTag("reorderer");
   }

   @Override
   public int perform() {
      return this.pC();
   }

   private int pC() {
      if (this.pC <= 0) {
         return 0;
      } else {
         this.pC--;
         int var1 = 0;
         if (bpl.pC(this.ctx)) {
            var1++;
            this.assignLocalFields(this.ctx);
         }

         cak var2 = new cak();
         var2.assignLocalFields(this.ctx);
         int var3 = var2.pC();
         Object[] var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bpl.pC(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         caj var4 = new caj();
         var4.assignLocalFields(this.ctx);
         var3 = var4.pC();
         var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bpl.pC(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         cai var5 = new cai();
         var5.assignLocalFields(this.ctx);
         var3 = var5.pC();
         var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bpl.pC(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         return var1;
      }
   }
}
