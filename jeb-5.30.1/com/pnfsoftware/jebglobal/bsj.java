package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.List;

public class bsj extends bsq implements brl {
   List oW;

   public bsj(bod var1, List var2) {
      super(bsq.eo.nf, bsq.CU.q);
      this.za = var1;
      this.oW = var2;
   }

   public boolean Dw() {
      return this.oW != null && !this.oW.isEmpty();
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bsj)) {
         throw new RuntimeException();
      } else {
         var4 = (IJavaStatement)var1.get(var2);
         if (!(var4 instanceof brp)) {
            throw new RuntimeException();
         } else {
            int[] var5 = new int[1];
            IJavaBlock var6 = (IJavaBlock)((brp)var4).q(var1, var2, var5);
            var2 = var5[0];
            IJavaTry var7 = (IJavaTry)this.za;
            var7.reset();
            var7.setTryBody(var6);
            ((bod)var7).oW = this.oW;
            var4 = (IJavaStatement)var1.get(var2++);
            if (!(var4 instanceof bsm)) {
               throw new RuntimeException();
            } else {
               while (true) {
                  var4 = (IJavaStatement)var1.get(var2++);
                  if (var4 instanceof bsl) {
                     var3[0] = var2;
                     return var7;
                  }

                  if (var4 instanceof bsk) {
                     IJavaType var8 = ((bsk)var4).Dw();
                     IJavaIdentifier var9 = ((bsk)var4).Uv();
                     IJavaDefinition var10 = ((bsk)var4).oW();
                     List var11 = ((bsk)var4).gO();
                     var4 = (IJavaStatement)var1.get(var2);
                     if (!(var4 instanceof brp)) {
                        throw new RuntimeException();
                     }

                     var6 = (IJavaBlock)((brp)var4).q(var1, var2, var5);
                     var2 = var5[0];
                     var7.addCatchBlock(var8, var11, var9, var10, var6);
                  } else {
                     if (!(var4 instanceof bsn)) {
                        throw new RuntimeException();
                     }

                     var4 = (IJavaStatement)var1.get(var2);
                     if (!(var4 instanceof brp)) {
                        throw new RuntimeException();
                     }

                     var6 = (IJavaBlock)((brp)var4).q(var1, var2, var5);
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
      return this.Dw() ? "try-with-res" : "try";
   }
}
