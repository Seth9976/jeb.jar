package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import java.util.List;

public class bog extends bop implements bnk {
   private IJavaExpression wS;

   public bog(bke var1, IJavaExpression var2) {
      super(bop.Av.ld, bop.Sv.pC);
      this.ys = var1;
      this.wS = var2;
   }

   public IJavaExpression A() {
      return this.wS;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bog)) {
         throw new RuntimeException();
      } else {
         IJavaExpression var5 = ((bog)var4).A();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((bno)var4).pC(var1, var2, var6);
            var2 = var6[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof boh)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaSynchronizedBlock var8 = (IJavaSynchronizedBlock)this.ys;
               var8.setLock(var5);
               var8.setBody(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "synchronized: " + this.wS;
   }
}
