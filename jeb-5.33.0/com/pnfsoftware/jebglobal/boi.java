package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.List;

public class boi extends bop implements bnk {
   List wS;

   public boi(bkg var1, List var2) {
      super(bop.Av.ys, bop.Sv.pC);
      this.ys = var1;
      this.wS = var2;
   }

   public boolean A() {
      return this.wS != null && !this.wS.isEmpty();
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof boi)) {
         throw new RuntimeException();
      } else {
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof bno)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            IJavaBlock var6 = (IJavaBlock)((bno)var4).pC(var1, var2, var5);
            var2 = var5[0];
            IJavaTry var7 = (IJavaTry)this.ys;
            var7.reset();
            var7.setTryBody(var6);
            ((bkg)var7).wS = this.wS;
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bol)) {
               throw new RuntimeException();
            } else {
               while (true) {
                  var4 = (IJavaStatement)var1.get(var2++);
                  if (var4 instanceof bok) {
                     var3[0] = var2;
                     return var7;
                  }

                  if (var4 instanceof boj) {
                     IJavaType var8 = ((boj)var4).A();
                     IJavaIdentifier var9 = ((boj)var4).kS();
                     IJavaDefinition var10 = ((boj)var4).wS();
                     List var11 = ((boj)var4).UT();
                     var4 = (IJavaStatement)var1.get(var2);
                     if (!(var4 instanceof bno)) {
                        throw new RuntimeException();
                     }

                     var6 = (IJavaBlock)((bno)var4).pC(var1, var2, var5);
                     var2 = var5[0];
                     var7.addCatchBlock(var8, var11, var9, var10, var6);
                  } else {
                     if (!(var4 instanceof bom)) {
                        throw new RuntimeException();
                     }

                     var4 = (IJavaStatement)var1.get(var2);
                     if (!(var4 instanceof bno)) {
                        throw new RuntimeException();
                     }

                     var6 = (IJavaBlock)((bno)var4).pC(var1, var2, var5);
                     var2 = var5[0];
                     var7.setFinallyBlock(var6);
                  }
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return this.A() ? "try-with-res" : "try";
   }
}
