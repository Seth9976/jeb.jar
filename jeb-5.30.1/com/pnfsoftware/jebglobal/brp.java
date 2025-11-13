package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class brp extends bsq implements brl {
   public brp(IJavaBlock var1) {
      super(bsq.eo.q, bsq.CU.q);
      this.za = var1;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof brp)) {
         throw new RuntimeException();
      } else {
         IJavaBlock var5 = (IJavaBlock)this.za;
         var5.reset();

         while (true) {
            var4 = (IJavaStatement)var1.get(var2);
            if (var4 instanceof brq) {
               var3[0] = ++var2;
               return var5;
            }

            if (var4 instanceof brl) {
               int[] var6 = new int[1];
               IJavaStatement var7 = ((brl)var4).q(var1, var2, var6);
               var5.add(var7);
               var2 = var6[0];
            } else {
               var5.add(var4);
               var2++;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "{";
   }
}
