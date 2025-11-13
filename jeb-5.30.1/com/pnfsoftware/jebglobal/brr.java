package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class brr extends bsq implements brl, brm {
   public brr(IJavaDoWhile var1) {
      super(bsq.eo.xK, bsq.CU.q);
      this.za = var1;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof brr)) {
         throw new RuntimeException();
      } else {
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            IJavaBlock var6 = (IJavaBlock)((brp)var4).q(var1, var2, var5);
            var2 = var5[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof brs)) {
               throw new RuntimeException();
            } else {
               IJavaPredicate var7 = ((brs)var4).q();
               var3[0] = var2;
               IJavaDoWhile var8 = (IJavaDoWhile)this.za;
               var8.setBody(var6);
               var8.setPredicate(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "do-while";
   }
}
