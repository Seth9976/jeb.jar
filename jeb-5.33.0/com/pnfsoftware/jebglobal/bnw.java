package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bnw extends bop implements bnk, bnn {
   IJavaPredicate wS;

   public bnw(IJavaIf var1, IJavaPredicate var2) {
      super(bop.Av.A, bop.Sv.pC);
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
      if (!(var4 instanceof bnw)) {
         throw new RuntimeException();
      } else {
         IJavaPredicate var5 = ((bnw)var4).pC();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((bno)var4).pC(var1, var2, var6);
            var2 = var6[0];
            IJavaIf var8 = (IJavaIf)this.ys;
            var8.reset();
            var8.addBranch(var5, var7);

            while (true) {
               var4 = (IJavaStatement)var1.get(var2++);
               if (var4 instanceof bnz) {
                  var3[0] = var2;
                  return var8;
               }

               if (var4 instanceof bnx) {
                  var5 = ((bnx)var4).pC();
                  var4 = (IJavaStatement)var1.get(var2);
                  if (!(var4 instanceof bno)) {
                     throw new RuntimeException();
                  }

                  var7 = (IJavaBlock)((bno)var4).pC(var1, var2, var6);
                  var2 = var6[0];
                  var8.addBranch(var5, var7);
               } else {
                  if (!(var4 instanceof bny)) {
                     throw new RuntimeException();
                  }

                  var4 = (IJavaStatement)var1.get(var2);
                  if (!(var4 instanceof bno)) {
                     throw new RuntimeException();
                  }

                  var7 = (IJavaBlock)((bno)var4).pC(var1, var2, var6);
                  var2 = var6[0];
                  var8.setDefaultBlock(var7);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return "if: " + this.wS;
   }
}
