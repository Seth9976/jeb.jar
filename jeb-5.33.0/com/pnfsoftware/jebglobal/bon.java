package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import java.util.List;

public class bon extends bop implements bnk, bnl, bnn {
   IJavaPredicate wS;

   public bon(IJavaWhile var1, IJavaPredicate var2) {
      super(bop.Av.wS, bop.Sv.pC);
      this.ys = var1;
      this.wS = var2;
   }

   @Override
   public IJavaPredicate pC() {
      return this.wS;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bon)) {
         throw new RuntimeException();
      } else {
         IJavaPredicate var5 = ((bon)var4).pC();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((bno)var4).pC(var1, var2, var6);
            var2 = var6[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof boo)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaWhile var8 = (IJavaWhile)this.ys;
               var8.setPredicate(var5);
               var8.setBody(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "while: " + this.wS;
   }
}
