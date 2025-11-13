package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bko extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.pC(this.m.getBody());
   }

   int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaIf var5) {
            if (this.pC(var1, var3, var5)) {
               var2++;
            } else if (this.A(var1, var3, var5)) {
               var2++;
            }
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var6);
            }
         }
      }

      return var2;
   }

   private boolean pC(IJavaBlock var1, int var2, IJavaIf var3) {
      IJavaBlock var4;
      if (var3.size() == 1
         && var2 + 1 < var1.size()
         && var1.get(var2 + 1) instanceof IJavaThrow
         && (var4 = var3.getBranchBody(0)).size() >= 1
         && JUtil.isFlowBreaker(var4.getLast())) {
         IJavaStatement var5 = var1.remove(var2 + 1);
         var4.insert(0, var5);
         IJavaPredicate var6 = var3.getBranchPredicate(0);
         var6.reverse(this.of);
         JUtil.moveStatements(var4, 1, var4.size(), var1, var2 + 1);
         return true;
      } else {
         return false;
      }
   }

   private boolean A(IJavaBlock var1, int var2, IJavaIf var3) {
      IJavaBlock var4;
      if (var3.size() == 1 && !(var4 = var3.getBranchBody(0)).isEmpty() && var4.getLast() instanceof IJavaGoto && var2 + 1 < var1.size()) {
         IJavaLabel var5 = ((IJavaGoto)var4.getLast()).getLabel();
         IJavaStatement var7 = var1.get(var2 + 1);
         IJavaIf var6;
         if (var7 instanceof IJavaIf && (var6 = (IJavaIf)var7).size() == 1) {
            IJavaBlock var8 = var6.getBranchBody(0);
            int var9 = bhu.pC(var5, var8);
            if (var9 < 0) {
               return false;
            } else {
               if (var9 > 0 && JUtil.isFlowBreaker(var8.get(var9 - 1))) {
                  var4.removeLast();
                  JUtil.moveStatements(var8, var9, var8.size(), var4, var4.size());
                  var3.addBranch(var6.getBranchPredicate(0), var6.getBranchBody(0));
                  var1.remove(var2 + 1);
               } else if (var2 + 2 < var1.size() && JUtil.isFlowBreaker(var1.get(var2 + 2))) {
                  var4.removeLast();
                  var3.addBranch(var6.getBranchPredicate(0), var6.getBranchBody(0));
                  var1.remove(var2 + 1);
                  IJavaBlock var16 = this.jctx.createBlock();
                  var16.add(var1.remove(var2 + 1));
                  var3.setDefaultBlock(var16);
                  JUtil.moveStatements(var8, var9, var8.size(), var1, var2 + 1);
               } else {
                  IJavaDefinition var10 = ((bjg)this.m.getIdentifierManager()).createDefinition(this.tf.getBoolean(), null);
                  IJavaIdentifier var11 = var10.getIdentifier();
                  IJavaAssignment var12 = this.jctx.createAssignment(var10, this.cf.createBoolean(false));
                  IJavaAssignment var13 = this.jctx.createAssignment(var11.duplicate(), this.cf.createBoolean(true));
                  IJavaAssignment var14 = var13.duplicate();
                  var4.removeLast();
                  var4.insert(0, var13);
                  IJavaBlock var15 = this.jctx.createBlock();
                  var15.add(var14);
                  var3.addBranch(var6.getBranchPredicate(0), var15);
                  JUtil.moveStatements(var8, 0, var9, var15, 1);
                  var1.insert(var2, var12);
                  var6.setBranchPredicate(0, this.jctx.createPredicate(var11));
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
