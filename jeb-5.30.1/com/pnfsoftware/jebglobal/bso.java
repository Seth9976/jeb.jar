package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import java.util.List;

public class bso extends bsq implements brl, brm, bro {
   IJavaPredicate oW;

   public bso(IJavaWhile var1, IJavaPredicate var2) {
      super(bsq.eo.Dw, bsq.CU.q);
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
      if (!(var4 instanceof bso)) {
         throw new RuntimeException();
      } else {
         IJavaPredicate var5 = ((bso)var4).q();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var6 = new int[1];
            IJavaBlock var7 = (IJavaBlock)((brp)var4).q(var1, var2, var6);
            var2 = var6[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bsp)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaWhile var8 = (IJavaWhile)this.za;
               var8.setPredicate(var5);
               var8.setBody(var7);
               return var8;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "while: " + this.oW;
   }
}
