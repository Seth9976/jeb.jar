package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bpr extends AbstractJBlockOptimizer {
   public bpr() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var4 >= 1 && var5 instanceof IJavaSwitch && ((IJavaSwitch)var5).isSwitchOnInteger()) {
            IJavaStatement var6 = var1.get(var4 - 1);
            if (var6 instanceof IJavaSwitch && ((IJavaSwitch)var6).isSwitchOnInteger()) {
               IJavaExpression var7 = ((IJavaSwitch)var6).getSwitchedExpression();
               if (var7 instanceof IJavaCall && ((IJavaCall)var7).getMethodName().equals("hashCode") && this.q(var1, var4, (IJavaSwitch)var6)) {
                  var3++;
                  continue;
               }
            }

            if (var6 instanceof IJavaIf && var4 >= 2) {
               IJavaStatement var9 = var1.get(var4 - 2);
               if (var9 instanceof IJavaAssignment && ((IJavaAssignment)var9).isSimpleAssignment()) {
                  IJavaExpression var8 = ((IJavaAssignment)var9).getRight();
                  if (var8 instanceof IJavaCall && ((IJavaCall)var8).getMethodName().equals("hashCode") && this.q(var1, var4, (IJavaIf)var6)) {
                     var3++;
                     continue;
                  }
               }
            }
         }

         var4++;
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaSwitch var3) {
      bpr.eo var4 = new bpr.eo();

      for (int var6 : var3.getCaseKeys()) {
         IJavaBlock var7 = var3.getCaseBody(var6);
         if (var7.size() >= 2 && var7.get(0) instanceof IJavaIf) {
            IJavaIf var8 = (IJavaIf)var7.get(0);
            IJavaPredicate var9 = var8.getBranchPredicate(0);
            if (this.q(var9, true, var7.get(1), var4) == null) {
               return false;
            }
         }
      }

      return this.q(var4, var1, var2, 1);
   }

   private boolean q(IJavaBlock var1, int var2, IJavaIf var3) {
      bpr.eo var4 = new bpr.eo();
      List var5 = var3.generateFlatList();

      for (int var6 = 0; var6 < var5.size() - 2; var6++) {
         IJavaStatement var7 = (IJavaStatement)var5.get(var6);
         if (var7 instanceof brx || var7 instanceof bry) {
            IJavaPredicate var8 = ((bro)var7).q();
            if (this.q(var8, false, (IJavaStatement)var5.get(var6 + 2), var4) == null) {
               return false;
            }
         }
      }

      return this.q(var4, var1, var2, 2);
   }

   private boolean q(bpr.eo var1, IJavaBlock var2, int var3, int var4) {
      if (!var1.q()) {
         return false;
      } else {
         IJavaSwitch var5 = (IJavaSwitch)var2.get(var3);
         if (var5.getSwitchedExpression() != var1.RF) {
            return false;
         } else {
            HashMap var6 = new HashMap();

            for (int var8 : var5.getCaseKeys()) {
               String var9 = (String)var1.xK.get(var8);
               if (var9 == null) {
                  return false;
               }

               var6.put(var8, var9);
            }

            var5.convertToSwitchOnString(var1.q, var6);
            int var10 = var3 - var4;

            while (var4-- > 0) {
               var2.remove(var10);
            }

            return true;
         }
      }
   }

   private bpr.eo q(IJavaPredicate var1, boolean var2, IJavaStatement var3, bpr.eo var4) {
      if (var1 instanceof IJavaOperation var5
         && var5.getLeft() == null
         && var5.getOperator().getOperatorType() == (var2 ? JavaOperatorType.LOG_NOT : JavaOperatorType.LOG_IDENT)) {
         IJavaExpression var6 = var5.getRight();
         if (var6 instanceof IJavaCall && ((IJavaCall)var6).getMethodName().equals("equals") && ((IJavaCall)var6).getArgumentCount() == 2) {
            IJavaExpression var7 = ((IJavaCall)var6).getArgument(0);
            IJavaExpression var8 = ((IJavaCall)var6).getArgument(1);
            if (var4.q != null) {
               if (var4.q != var7) {
                  return null;
               }
            } else {
               var4.q = var7;
            }

            if (var8 instanceof IJavaConstant && ((IJavaConstant)var8).getString() != null) {
               String var9 = ((IJavaConstant)var8).getString();
               if (var3 instanceof IJavaAssignment
                  && ((IJavaAssignment)var3).isSimpleAssignment()
                  && ((IJavaAssignment)var3).getRight() instanceof IJavaConstant) {
                  IJavaConstant var10 = (IJavaConstant)((IJavaAssignment)var3).getRight();
                  int var11 = var10.getInt();
                  IJavaIdentifier var12 = JUtil.getIdentifier(((IJavaAssignment)var3).getLeft());
                  if (var4.RF != null) {
                     if (var4.RF != var12) {
                        return null;
                     }
                  } else {
                     var4.RF = var12;
                  }

                  if (var4.xK.put(var11, var9) != null) {
                     return null;
                  }

                  return var4;
               }
            }
         }
      }

      return var4;
   }

   private static class eo {
      IJavaExpression q;
      IJavaExpression RF;
      Map xK = new HashMap();

      boolean q() {
         return this.q != null && this.RF != null && !this.xK.isEmpty();
      }
   }
}
