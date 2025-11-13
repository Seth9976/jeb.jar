package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bns extends bop implements bnk, bnl, bnn {
   IJavaStatement wS;
   IJavaPredicate UT;
   IJavaStatement E;

   public bns(IJavaFor var1, IJavaStatement var2, IJavaPredicate var3, IJavaStatement var4) {
      super(bop.Av.UT, bop.Sv.pC);
      this.ys = var1;
      this.wS = var2;
      this.UT = var3;
      this.E = var4;
   }

   public IJavaStatement A() {
      return this.wS;
   }

   @Override
   public IJavaPredicate pC() {
      return this.UT;
   }

   public IJavaStatement kS() {
      return this.E;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bns)) {
         throw new RuntimeException();
      } else {
         IJavaStatement var5 = ((bns)var4).A();
         IJavaPredicate var6 = ((bns)var4).pC();
         IJavaStatement var7 = ((bns)var4).kS();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var8 = new int[1];
            IJavaBlock var9 = (IJavaBlock)((bno)var4).pC(var1, var2, var8);
            var2 = var8[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bnv)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaFor var10 = (IJavaFor)this.ys;
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
      return "for: " + this.wS + " / " + this.UT + " / " + this.E;
   }
}
