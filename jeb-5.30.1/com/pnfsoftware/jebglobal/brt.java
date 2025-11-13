package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class brt extends bsq implements brl, brm, bro {
   IJavaStatement oW;
   IJavaPredicate gO;
   IJavaStatement nf;

   public brt(IJavaFor var1, IJavaStatement var2, IJavaPredicate var3, IJavaStatement var4) {
      super(bsq.eo.Uv, bsq.CU.q);
      this.za = var1;
      this.oW = var2;
      this.gO = var3;
      this.nf = var4;
   }

   public IJavaStatement Dw() {
      return this.oW;
   }

   @Override
   public IJavaPredicate q() {
      return this.gO;
   }

   public IJavaStatement Uv() {
      return this.nf;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof brt)) {
         throw new RuntimeException();
      } else {
         IJavaStatement var5 = ((brt)var4).Dw();
         IJavaPredicate var6 = ((brt)var4).q();
         IJavaStatement var7 = ((brt)var4).Uv();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var8 = new int[1];
            IJavaBlock var9 = (IJavaBlock)((brp)var4).q(var1, var2, var8);
            var2 = var8[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof brw)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaFor var10 = (IJavaFor)this.za;
               var10.setInitializer(var5);
               var10.setPredicate(var6);
               var10.setPostStatement(var7);
               var10.setBody(var9);
               return var10;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "for: " + this.oW + " / " + this.gO + " / " + this.nf;
   }
}
