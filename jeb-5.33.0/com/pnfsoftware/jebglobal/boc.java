package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class boc extends bop implements bnk, bnl {
   IJavaExpression wS;
   int UT;
   Map E;
   Map sY;

   public boc(bkc var1) {
      super(bop.Av.sY, bop.Sv.pC);
      this.ys = var1;
      this.wS = var1.getSwitchedExpression();
      this.UT = var1.getSwitchType();
      this.sY = var1.kS();
      if (var1.isSwitchOnEnum()) {
         this.E = var1.getEnumMap();
      }
   }

   public IJavaExpression A() {
      return this.wS;
   }

   @Override
   public IJavaStatement pC(List var1, int var2, int[] var3) {
      IJavaStatement var4 = (IJavaStatement)var1.get(var2++);
      if (!(var4 instanceof boc)) {
         throw new RuntimeException();
      } else {
         IJavaExpression var5 = ((boc)var4).A();
         int var6 = ((boc)var4).UT;
         int[] var7 = new int[1];
         IJavaSwitch var9 = (IJavaSwitch)this.ys;
         var9.reset(var6);
         var9.setSwitchedExpression(var5);

         while (true) {
            var4 = (IJavaStatement)var1.get(var2++);
            if (var4 instanceof bof) {
               if (var6 == 1) {
                  var9.convertToSwitchOnEnum(null, this.E);
               }

               ((bkc)var9).pC(this.sY);
               var3[0] = var2;
               return var9;
            }

            if (var4 instanceof bod) {
               List var10 = ((bod)var4).A();
               List var11 = ((bod)var4).kS();
               var4 = (IJavaStatement)var1.get(var2);
               if (!(var4 instanceof bno)) {
                  throw new RuntimeException();
               }

               IJavaBlock var19 = (IJavaBlock)((bno)var4).pC(var1, var2, var7);
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
               if (!(var4 instanceof boe)) {
                  throw new RuntimeException();
               }

               var4 = (IJavaStatement)var1.get(var2);
               if (!(var4 instanceof bno)) {
                  throw new RuntimeException();
               }

               IJavaBlock var8 = (IJavaBlock)((bno)var4).pC(var1, var2, var7);
               var2 = var7[0];
               var9.setDefaultBlock(var8);
            }
         }
      }
   }

   @Override
   public String toString() {
      return "switch: " + this.wS;
   }
}
