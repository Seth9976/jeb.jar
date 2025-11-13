package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class brx extends bsq implements brl, bro {
   IJavaPredicate oW;

   public brx(IJavaIf var1, IJavaPredicate var2) {
      super(bsq.eo.RF, bsq.CU.q);
      this.za = var1;
      this.oW = var2;
   }

   @Override
   public IJavaPredicate q() {
      return this.oW;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof brx)) {
         throw new RuntimeException();
      } else {
         IJavaPredicate var5 = ((brx)var4).q();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((brp)var4).q(var1, var2, var6);
            var2 = var6[0];
            IJavaIf var8 = (IJavaIf)this.za;
            var8.reset();
            var8.addBranch(var5, var7);

            while (true) {
               var4 = (IJavaStatement)var1.get(var2++);
               if (var4 instanceof bsa) {
                  var3[0] = var2;
                  return var8;
               }

               if (var4 instanceof bry) {
                  var5 = ((bry)var4).q();
                  var4 = (IJavaStatement)var1.get(var2);
                  if (!(var4 instanceof brp)) {
                     throw new RuntimeException();
                  }

                  var7 = (IJavaBlock)((brp)var4).q(var1, var2, var6);
                  var2 = var6[0];
                  var8.addBranch(var5, var7);
               } else {
                  if (!(var4 instanceof brz)) {
                     throw new RuntimeException();
                  }

                  var4 = (IJavaStatement)var1.get(var2);
                  if (!(var4 instanceof brp)) {
                     throw new RuntimeException();
                  }

                  var7 = (IJavaBlock)((brp)var4).q(var1, var2, var6);
                  var2 = var6[0];
                  var8.setDefaultBlock(var7);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return "if: " + this.oW;
   }
}
