package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bno extends bop implements bnk {
   public bno(IJavaBlock var1) {
      super(bop.Av.pC, bop.Sv.pC);
      this.ys = var1;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bno)) {
         throw new RuntimeException();
      } else {
         IJavaBlock var5 = (IJavaBlock)this.ys;
         var5.reset();

         while (true) {
            var4 = (IJavaStatement)var1.get(var2);
            if (var4 instanceof bnp) {
               var3[0] = ++var2;
               return var5;
            }

            if (var4 instanceof bnk) {
               int[] var6 = new int[1];
               IJavaStatement var7 = ((bnk)var4).pC(var1, var2, var6);
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
