package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericWhileLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.base.Assert;

public class akr extends aie {
   @Override
   protected int q(ICGenericWhileLoop var1) {
      if (var1 instanceof ICWhileStm && !var1.getPredicate().isLitteralTrue()) {
         return 0;
      } else {
         ICBlock var2 = var1.getBody();
         int var3 = var2.size();
         return var3 >= 1 && var2.getLast() instanceof ICIfStm ? 1 : 0;
      }
   }

   @Override
   protected aie.eo q(ICBlock var1, ICGenericWhileLoop var2, int var3, ICLabel var4, int var5) {
      akr.eo var6 = new akr.eo(this.m, var1, var2, var3, var4, var5);
      return var6.q();
   }

   private class eo {
      private final ICBlock RF;
      private final ICGenericWhileLoop xK;
      private final ICLabel Dw;
      private final int Uv;
      private ICBlock oW;
      private ICStatement gO;
      private ICIfStm nf;

      public eo(ICMethod var2, ICBlock var3, ICGenericWhileLoop var4, int var5, ICLabel var6, int var7) {
         this.RF = var3;
         this.xK = var4;
         this.Dw = var6;
         this.Uv = var7;
         this.gO = var4.getBody().getLast();
         this.oW = var4.getBody();
         this.nf = null;
      }

      public aie.eo q() {
         aie.eo var1 = new aie.eo();
         if (this.Uv == 1) {
            this.RF();
            if (this.gO instanceof ICIfStm) {
               var1.q = this.xK();
            }

            return var1;
         } else {
            return var1;
         }
      }

      public boolean q(ICStatement var1) {
         return CUtil.isGotoTo(var1, this.Dw) || CUtil.isPlainBreak(var1) || CUtil.isBreakTo(var1, this.Dw);
      }

      private void RF() {
         while (CUtil.isIfElse(this.gO)) {
            ICIfStm var1 = (ICIfStm)this.gO;
            ICBlock var2 = var1.getBranchBody(0);
            ICBlock var3 = var1.getDefaultBlock();
            if (!var2.isEmpty() && !var3.isEmpty()) {
               if (this.RF(var2.getLast())) {
                  if (!this.q(var3.get(0))) {
                     this.oW = var3;
                     this.gO = this.oW.getLast();
                     this.nf = var1;
                     continue;
                  } else if (CUtil.hasNoSideEffects(var1.getBranchPredicate(0))) {
                     this.gO = akr.this.ef.createIfStm(var1.getBranchPredicate(0).duplicateAndReverse(akr.this.of), var3);
                     this.oW.add(this.gO);
                     var1.setDefaultBlock(null);
                  }
               } else if (this.RF(var3.getLast())) {
                  if (!this.q(var2.get(0))) {
                     this.oW = var2;
                     this.gO = this.oW.getLast();
                     this.nf = null;
                     continue;
                  } else if (CUtil.hasNoSideEffects(var1.getBranchPredicate(0))) {
                     ICIfStm var4 = akr.this.ef.createIfStm(var1.getBranchPredicate(0).duplicateAndReverse(akr.this.of), var3);
                     this.oW.removeLast();
                     this.oW.add(var4);
                     this.gO = akr.this.ef.createIfStm(var1.getBranchPredicate(0), var2);
                     this.oW.add(this.gO);
                  }
               }
            }
            break;
         }
      }

      private boolean RF(ICStatement var1) {
         return (var1 instanceof ICReturn || var1 instanceof ICGoto) && !this.q(var1);
      }

      private int xK() {
         Assert.a(this.gO instanceof ICIfStm);
         int var1 = afc.RF((ICIfStm)this.gO, this.Dw);
         if (var1 == -1) {
            return 0;
         } else {
            return !this.q(var1) ? 0 : this.RF(var1);
         }
      }

      private int RF(int var1) {
         if (!(this.xK instanceof ICDoWhileStm var2)) {
            var2 = akr.this.ef.createDoWhileStm(this.xK.getBody(), this.xK.getPredicate());
            this.RF.replaceSubElement(this.xK, var2);
         }

         return this.q(var2, var1) ? 1 : 0;
      }

      private boolean q(ICDoWhileStm var1, int var2) {
         ICIfStm var3 = (ICIfStm)this.gO;
         ICPredicate var4 = var3.getBranchPredicate(var3.size() - 1);
         var3.removeBranch(var3.size() - 1);
         if (var3.size() == 0) {
            this.oW.removeLast();
            if (this.nf != null && this.oW.isEmpty()) {
               this.nf.setDefaultBlock(null);
            }
         }

         if (var1.getPredicate().isLitteralTrue()) {
            var4.reverse(akr.this.of);
            var1.setPredicate(var4);
         } else {
            var1.setPredicate(
               akr.this.ef.createPredicate(CUtil.andL(akr.this.m, CUtil.notL(akr.this.m, var4.getExpression()), var1.getPredicate().getExpression()))
            );
         }

         return true;
      }

      public boolean q(int var1) {
         return !((ICIfStm)this.gO).hasDefaultBlock() && var1 == ((ICIfStm)this.gO).size() - 1;
      }
   }
}
