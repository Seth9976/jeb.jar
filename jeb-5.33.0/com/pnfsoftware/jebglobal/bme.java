package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bme extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaAssignment var6) {
            IJavaLeftExpression var7 = var6.getLeft();
            if (var7 instanceof IJavaIdentifier || var7 instanceof IJavaInstanceField || var7 instanceof IJavaStaticField || var7 instanceof IJavaArrayElt) {
               IJavaLeftExpression var8 = var6.getLeft();
               if (var6.isSimpleAssignment() && var6.getRight() instanceof IJavaOperation) {
                  IJavaOperation var9 = (IJavaOperation)var6.getRight();
                  IJavaOperator var10 = var9.getOperator();
                  if (var10.isValidForCombinedAssignment()) {
                     IJavaExpression var11 = null;
                     if (var9.getLeft().equals(var8)) {
                        var11 = var9.getRight();
                     } else if (var9.getRight().equals(var8)
                        && var10.isAnyOf(JavaOperatorType.MUL, JavaOperatorType.OR, JavaOperatorType.AND, JavaOperatorType.XOR)) {
                        var11 = var9.getLeft();
                     }

                     if (var11 != null) {
                        var6.setCombinedOperatorAssignment(var9.getOperator(), var11);
                        var3++;
                     }
                  }
               }

               if (var6.isCombinedOperatorAssignment() && var6.getRight() instanceof IJavaConstant) {
                  IJavaOperator var12 = var6.getCombinedOperator();
                  if (var12.isAnyOf(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
                     IJavaConstant var13 = (IJavaConstant)var6.getRight();
                     Boolean var14 = null;
                     if (var13.isOne()) {
                        var14 = var12.is(JavaOperatorType.ADD);
                     } else if (var13.isMinusOne()) {
                        var14 = var12.is(JavaOperatorType.SUB);
                     }

                     if (var14 != null) {
                        var6.setUnaryOperator(var14, true);
                        var3++;
                     }
                  }
               }
            }
         }
      }

      return var3;
   }
}
