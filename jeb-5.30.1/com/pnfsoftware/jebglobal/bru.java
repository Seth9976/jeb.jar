package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bru extends bsq implements brl, brm {
   IJavaDefinition oW;
   IJavaExpression gO;

   public bru(IJavaForEach var1, IJavaDefinition var2, IJavaExpression var3) {
      super(bsq.eo.oW, bsq.CU.q);
      this.za = var1;
      this.oW = var2;
      this.gO = var3;
   }

   public IJavaDefinition Dw() {
      return this.oW;
   }

   public IJavaExpression Uv() {
      return this.gO;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bru)) {
         throw new RuntimeException();
      } else {
         IJavaDefinition var5 = ((bru)var4).Dw();
         IJavaExpression var6 = ((bru)var4).Uv();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var7 = new int[1];
            IJavaBlock var8 = (IJavaBlock)((brp)var4).q(var1, var2, var7);
            var2 = var7[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof brv)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaForEach var9 = (IJavaForEach)this.za;
               var9.setVariable(var5);
               var9.setIterable(var6);
               var9.setBody(var8);
               return var9;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "for-each: " + this.oW + ": " + this.gO;
   }
}
