package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bnq extends bop implements bnk, bnl {
   public bnq(IJavaDoWhile var1) {
      super(bop.Av.kS, bop.Sv.pC);
      this.ys = var1;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bnq)) {
         throw new RuntimeException();
      } else {
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            IJavaBlock var6 = (IJavaBlock)((bno)var4).pC(var1, var2, var5);
            var2 = var5[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bnr)) {
               throw new RuntimeException();
            } else {
               IJavaPredicate var7 = ((bnr)var4).pC();
               var3[0] = var2;
               IJavaDoWhile var8 = (IJavaDoWhile)this.ys;
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
