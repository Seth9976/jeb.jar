package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericWhileLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.base.Assert;

public class ait extends agl {
   @Override
   protected agl.Av pC(ICBlock var1, ICGenericWhileLoop var2, int var3, ICLabel var4, int var5) {
      ait.Av var6 = new ait.Av(this.m, var1, var2, var3, var4, var5);
      return var6.pC();
   }

   @Override
   protected int pC(ICGenericWhileLoop var1) {
      if (var1 instanceof ICDoWhileStm && !var1.getPredicate().isLitteralTrue()) {
         return 0;
      } else {
         return !A(var1) && !kS(var1) ? 0 : 1;
      }
   }

   private static boolean A(ICGenericWhileLoop var0) {
      return var0.getBody().get(0) instanceof ICIfStm;
   }

   private static boolean kS(ICGenericWhileLoop var0) {
      return var0.getPredicate().isLitteralTrue()
         && var0.getBody().size() == 2
         && var0.getBody().getLast() instanceof ICIfStm
         && var0.getBody().get(0) instanceof ICAssignment;
   }

   private class Av {
      private final ICBlock A;
      private final ICGenericWhileLoop kS;
      private final int wS;
      private final ICLabel UT;
      private final int E;
      private boolean sY;

      Av(ICMethod var2, ICBlock var3, ICGenericWhileLoop var4, int var5, ICLabel var6, int var7) {
         this.A = var3;
         this.kS = var4;
         this.wS = var5;
         this.UT = var6;
         this.E = var7;
      }

      public agl.Av pC() {
         agl.Av var1 = new agl.Av();
         if (this.E != 1) {
            return var1;
         } else {
            if (ait.A(this.kS)) {
               ICIfStm var2 = (ICIfStm)this.kS.getBody().get(0);
               var1.pC = this.pC(var2);
            }

            if (var1.pC != 0) {
               return var1;
            } else {
               if (ait.kS(this.kS)) {
                  ICIfStm var3 = (ICIfStm)this.kS.getBody().getLast();
                  this.sY = true;
                  var1.pC = this.pC(var3);
                  if (var1.pC != 0) {
                     var1.A = 2;
                  }
               }

               return var1;
            }
         }
      }

      private int pC(ICIfStm var1) {
         int var2 = adj.pC(var1, this.UT);
         if (var2 == -1) {
            return 0;
         } else {
            return !this.pC(var1, var2) ? 0 : this.A(var1, var2);
         }
      }

      private int A(ICIfStm var1, int var2) {
         if (!(this.kS instanceof ICWhileStm var3)) {
            var3 = ait.this.ef.createWhileStm(this.kS.getPredicate(), this.kS.getBody());
            this.A.replaceSubElement(this.kS, var3);
         }

         if (this.sY) {
            this.pC(var3);
         }

         return this.pC(var3, var1, var2) ? 1 : 0;
      }

      private void pC(ICWhileStm var1) {
         ICAssignment var2 = (ICAssignment)var1.getBody().remove(0);
         this.A.insert(this.wS, var2);
         ICAssignment var3 = var2.duplicate(true);
         var1.getBody().add(var3);
      }

      private boolean pC(ICWhileStm var1, ICIfStm var2, int var3) {
         ICPredicate var4;
         if (var2.size() == 1) {
            Assert.a(var3 == 0);
            var4 = var2.getBranchPredicate(0);
            var1.getBody().remove(0);
         } else if (var2.size() == 2) {
            Assert.a(var3 < 2);
            if (var3 == 0) {
               var4 = var2.getBranchPredicate(0);
               if (var2.hasDefaultBlock()) {
                  var1.getBody().insertAll(0, (ICBlock)var2.getBlocks().get(1));
                  var1.getBody().remove(var2);
               } else {
                  var2.removeBranch(0);
               }
            } else {
               var4 = var2.getBranchPredicate(0);
               var4.reverse(ait.this.of);
               var1.getBody().insertAll(0, (ICBlock)var2.getBlocks().get(0));
               var1.getBody().remove(var2);
            }
         } else {
            Assert.a(var3 == 0);
            var4 = var2.getBranchPredicate(0);
            var2.removeBranch(0);
         }

         if (var1.getPredicate().isLitteralTrue()) {
            var4.reverse(ait.this.of);
            var1.setPredicate(var4);
         } else {
            var1.setPredicate(
               ait.this.ef.createPredicate(CUtil.andL(ait.this.m, var1.getPredicate().getExpression(), CUtil.notL(ait.this.m, var4.getExpression())))
            );
         }

         return true;
      }

      public boolean pC(ICIfStm var1, int var2) {
         if (var1.size() > 2 && var2 != 0) {
            return false;
         } else if (var1.size() == 2) {
            return !var1.hasDefaultBlock() ? var2 == 0 : true;
         } else {
            return true;
         }
      }
   }
}
