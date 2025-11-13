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

public class aks extends aie {
   @Override
   protected aie.eo q(ICBlock var1, ICGenericWhileLoop var2, int var3, ICLabel var4, int var5) {
      aks.eo var6 = new aks.eo(this.m, var1, var2, var3, var4, var5);
      return var6.q();
   }

   @Override
   protected int q(ICGenericWhileLoop var1) {
      if (var1 instanceof ICDoWhileStm && !var1.getPredicate().isLitteralTrue()) {
         return 0;
      } else {
         return !RF(var1) && !xK(var1) ? 0 : 1;
      }
   }

   private static boolean RF(ICGenericWhileLoop var0) {
      return var0.getBody().get(0) instanceof ICIfStm;
   }

   private static boolean xK(ICGenericWhileLoop var0) {
      return var0.getPredicate().isLitteralTrue()
         && var0.getBody().size() == 2
         && var0.getBody().getLast() instanceof ICIfStm
         && var0.getBody().get(0) instanceof ICAssignment;
   }

   private class eo {
      private final ICBlock RF;
      private final ICGenericWhileLoop xK;
      private final int Dw;
      private final ICLabel Uv;
      private final int oW;
      private boolean gO;

      eo(ICMethod var2, ICBlock var3, ICGenericWhileLoop var4, int var5, ICLabel var6, int var7) {
         this.RF = var3;
         this.xK = var4;
         this.Dw = var5;
         this.Uv = var6;
         this.oW = var7;
      }

      public aie.eo q() {
         aie.eo var1 = new aie.eo();
         if (this.oW != 1) {
            return var1;
         } else {
            if (aks.RF(this.xK)) {
               ICIfStm var2 = (ICIfStm)this.xK.getBody().get(0);
               var1.q = this.q(var2);
            }

            if (var1.q != 0) {
               return var1;
            } else {
               if (aks.xK(this.xK)) {
                  ICIfStm var3 = (ICIfStm)this.xK.getBody().getLast();
                  this.gO = true;
                  var1.q = this.q(var3);
                  if (var1.q != 0) {
                     var1.RF = 2;
                  }
               }

               return var1;
            }
         }
      }

      private int q(ICIfStm var1) {
         int var2 = afc.q(var1, this.Uv);
         if (var2 == -1) {
            return 0;
         } else {
            return !this.q(var1, var2) ? 0 : this.RF(var1, var2);
         }
      }

      private int RF(ICIfStm var1, int var2) {
         if (!(this.xK instanceof ICWhileStm var3)) {
            var3 = aks.this.ef.createWhileStm(this.xK.getPredicate(), this.xK.getBody());
            this.RF.replaceSubElement(this.xK, var3);
         }

         if (this.gO) {
            this.q(var3);
         }

         return this.q(var3, var1, var2) ? 1 : 0;
      }

      private void q(ICWhileStm var1) {
         ICAssignment var2 = (ICAssignment)var1.getBody().remove(0);
         this.RF.insert(this.Dw, var2);
         ICAssignment var3 = var2.duplicate(true);
         var1.getBody().add(var3);
      }

      private boolean q(ICWhileStm var1, ICIfStm var2, int var3) {
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
               var4.reverse(aks.this.of);
               var1.getBody().insertAll(0, (ICBlock)var2.getBlocks().get(0));
               var1.getBody().remove(var2);
            }
         } else {
            Assert.a(var3 == 0);
            var4 = var2.getBranchPredicate(0);
            var2.removeBranch(0);
         }

         if (var1.getPredicate().isLitteralTrue()) {
            var4.reverse(aks.this.of);
            var1.setPredicate(var4);
         } else {
            var1.setPredicate(
               aks.this.ef.createPredicate(CUtil.andL(aks.this.m, var1.getPredicate().getExpression(), CUtil.notL(aks.this.m, var4.getExpression())))
            );
         }

         return true;
      }

      public boolean q(ICIfStm var1, int var2) {
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
