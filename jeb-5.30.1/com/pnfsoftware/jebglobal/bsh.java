package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import java.util.List;

public class bsh extends bsq implements brl {
   private IJavaExpression oW;

   public bsh(bob var1, IJavaExpression var2) {
      super(bsq.eo.gP, bsq.CU.q);
      this.za = var1;
      this.oW = var2;
   }

   public IJavaExpression Dw() {
      return this.oW;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bsh)) {
         throw new RuntimeException();
      } else {
         IJavaExpression var5 = ((bsh)var4).Dw();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((brp)var4).q(var1, var2, var6);
            var2 = var6[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bsi)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaSynchronizedBlock var8 = (IJavaSynchronizedBlock)this.za;
               var8.setLock(var5);
               var8.setBody(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "synchronized: " + this.oW;
   }
}
