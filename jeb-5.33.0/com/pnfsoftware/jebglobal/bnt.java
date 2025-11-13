package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bnt extends bop implements bnk, bnl {
   IJavaDefinition wS;
   IJavaExpression UT;

   public bnt(IJavaForEach var1, IJavaDefinition var2, IJavaExpression var3) {
      super(bop.Av.E, bop.Sv.pC);
      this.ys = var1;
      this.wS = var2;
      this.UT = var3;
   }

   public IJavaDefinition A() {
      return this.wS;
   }

   public IJavaExpression kS() {
      return this.UT;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bnt)) {
         throw new RuntimeException();
      } else {
         IJavaDefinition var5 = ((bnt)var4).A();
         IJavaExpression var6 = ((bnt)var4).kS();
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var7 = new int[1];
            IJavaBlock var8 = (IJavaBlock)((bno)var4).pC(var1, var2, var7);
            var2 = var7[0];
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bnu)) {
               throw new RuntimeException();
            } else {
               var3[0] = var2;
               IJavaForEach var9 = (IJavaForEach)this.ys;
               var9.setVariable(var5);
               var9.setIterable(var6);
               var9.setBody(var8);
               return var9;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "for-each: " + this.wS + ": " + this.UT;
   }
}
