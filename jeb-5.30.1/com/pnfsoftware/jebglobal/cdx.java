package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;

public class cdx extends AbstractDOptimizer {
   private int q = 1;

   public cdx() {
      this.addTag("reorderer");
   }

   @Override
   public int perform() {
      return this.q();
   }

   private int q() {
      if (this.q <= 0) {
         return 0;
      } else {
         this.q--;
         int var1 = 0;
         if (bto.q(this.ctx)) {
            var1++;
            this.assignLocalFields(this.ctx);
         }

         cfd var2 = new cfd();
         var2.assignLocalFields(this.ctx);
         int var3 = var2.q();
         Object[] var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bto.q(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         cfc var4 = new cfc();
         var4.assignLocalFields(this.ctx);
         var3 = var4.q();
         var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bto.q(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         cfb var5 = new cfb();
         var5.assignLocalFields(this.ctx);
         var3 = var5.q();
         var10000 = new Object[]{var3};
         if (var3 > 0) {
            var1 += var3;
            if (bto.q(this.ctx)) {
               var1++;
               this.assignLocalFields(this.ctx);
            }
         }

         return var1;
      }
   }

   private int RF() {
      if (this.q <= 0) {
         return 0;
      } else {
         this.q--;
         int var1 = 0;
         cfd var2 = new cfd();
         var1 += var2.perform(this.ctx);
         cfc var3 = new cfc();
         var1 += var3.perform(this.ctx);
         cfb var4 = new cfb();
         return var1 + var4.perform(this.ctx);
      }
   }
}
