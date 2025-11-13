package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bsd extends bsq implements brl, brm {
   IJavaExpression oW;
   int gO;
   Map nf;
   Map gP;

   public bsd(bnz var1) {
      super(bsq.eo.gO, bsq.CU.q);
      this.za = var1;
      this.oW = var1.getSwitchedExpression();
      this.gO = var1.getSwitchType();
      this.gP = var1.Uv();
      if (var1.isSwitchOnEnum()) {
         this.nf = var1.getEnumMap();
      }
   }

   public IJavaExpression Dw() {
      return this.oW;
   }

   @Override
   public IJavaStatement q(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof bsd)) {
         throw new RuntimeException();
      } else {
         IJavaExpression var5 = ((bsd)var4).Dw();
         int var6 = ((bsd)var4).gO;
         int[] var7 = new int[1];
         IJavaSwitch var9 = (IJavaSwitch)this.za;
         var9.reset(var6);
         var9.setSwitchedExpression(var5);

         while (true) {
            var4 = (IJavaStatement)var1.get(var2++);
            if (var4 instanceof bsg) {
               if (var6 == 1) {
                  var9.convertToSwitchOnEnum(null, this.nf);
               }

               ((bnz)var9).q(this.gP);
               var3[0] = var2;
               return var9;
            }

            if (var4 instanceof bse) {
               List var10 = ((bse)var4).Dw();
               List var11 = ((bse)var4).Uv();
               var4 = (IJavaStatement)var1.get(var2);
               if (!(var4 instanceof brp)) {
                  throw new RuntimeException();
               }

               IJavaBlock var19 = (IJavaBlock)((brp)var4).q(var1, var2, var7);
               var2 = var7[0];
               if (var11 == null) {
                  var9.addCase(var10, var19);
               } else {
                  HashMap var12 = new HashMap();

                  for (int var13 = 0; var13 < var10.size(); var13++) {
                     var12.put((Integer)var10.get(var13), (String)var11.get(var13));
                  }

                  var9.addCase(var12, var19);
               }
            } else {
               if (!(var4 instanceof bsf)) {
                  throw new RuntimeException();
               }

               var4 = (IJavaStatement)var1.get(var2);
               if (!(var4 instanceof brp)) {
                  throw new RuntimeException();
               }

               IJavaBlock var8 = (IJavaBlock)((brp)var4).q(var1, var2, var7);
               var2 = var7[0];
               var9.setDefaultBlock(var8);
            }
         }
      }
   }

   @Override
   public String toString() {
      return "switch: " + this.oW;
   }
}
