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

public class ais extends agl {
   @Override
   protected int pC(ICGenericWhileLoop var1) {
      if (var1 instanceof ICWhileStm && !var1.getPredicate().isLitteralTrue()) {
         return 0;
      } else {
         ICBlock var2 = var1.getBody();
         int var3 = var2.size();
         return var3 >= 1 && var2.getLast() instanceof ICIfStm ? 1 : 0;
      }
   }

   @Override
   protected agl.Av pC(ICBlock var1, ICGenericWhileLoop var2, int var3, ICLabel var4, int var5) {
      ais.Av var6 = new ais.Av(this.m, var1, var2, var3, var4, var5);
      return var6.pC();
   }

   private class Av {
      private final ICBlock A;
      private final ICGenericWhileLoop kS;
      private final ICLabel wS;
      private final int UT;
      private ICBlock E;
      private ICStatement sY;
      private ICIfStm ys;

      public Av(ICMethod var2, ICBlock var3, ICGenericWhileLoop var4, int var5, ICLabel var6, int var7) {
         this.A = var3;
         this.kS = var4;
         this.wS = var6;
         this.UT = var7;
         this.sY = var4.getBody().getLast();
         this.E = var4.getBody();
         this.ys = null;
      }

      public agl.Av pC() {
         agl.Av var1 = new agl.Av();
         if (this.UT == 1) {
            this.A();
            if (this.sY instanceof ICIfStm) {
               var1.pC = this.kS();
            }

            return var1;
         } else {
            return var1;
         }
      }

      public boolean pC(ICStatement var1) {
         return CUtil.isGotoTo(var1, this.wS) || CUtil.isPlainBreak(var1) || CUtil.isBreakTo(var1, this.wS);
      }

      private void A() {
         while (CUtil.isIfElse(this.sY)) {
            ICIfStm var1 = (ICIfStm)this.sY;
            ICBlock var2 = var1.getBranchBody(0);
            ICBlock var3 = var1.getDefaultBlock();
            if (!var2.isEmpty() && !var3.isEmpty()) {
               if (this.A(var2.getLast())) {
                  if (!this.pC(var3.get(0))) {
                     this.E = var3;
                     this.sY = this.E.getLast();
                     this.ys = var1;
                     continue;
                  } else if (CUtil.hasNoSideEffects(var1.getBranchPredicate(0))) {
                     this.sY = ais.this.ef.createIfStm(var1.getBranchPredicate(0).duplicateAndReverse(ais.this.of), var3);
                     this.E.add(this.sY);
                     var1.setDefaultBlock(null);
                  }
               } else if (this.A(var3.getLast())) {
                  if (!this.pC(var2.get(0))) {
                     this.E = var2;
                     this.sY = this.E.getLast();
                     this.ys = null;
                     continue;
                  } else if (CUtil.hasNoSideEffects(var1.getBranchPredicate(0))) {
                     ICIfStm var4 = ais.this.ef.createIfStm(var1.getBranchPredicate(0).duplicateAndReverse(ais.this.of), var3);
                     this.E.removeLast();
                     this.E.add(var4);
                     this.sY = ais.this.ef.createIfStm(var1.getBranchPredicate(0), var2);
                     this.E.add(this.sY);
                  }
               }
            }
            break;
         }
      }

      private boolean A(ICStatement var1) {
         return (var1 instanceof ICReturn || var1 instanceof ICGoto) && !this.pC(var1);
      }

      private int kS() {
         Assert.a(this.sY instanceof ICIfStm);
         int var1 = adj.A((ICIfStm)this.sY, this.wS);
         if (var1 == -1) {
            return 0;
         } else {
            return !this.pC(var1) ? 0 : this.A(var1);
         }
      }

      private int A(int var1) {
         if (!(this.kS instanceof ICDoWhileStm var2)) {
            var2 = ais.this.ef.createDoWhileStm(this.kS.getBody(), this.kS.getPredicate());
            this.A.replaceSubElement(this.kS, var2);
         }

         return this.pC(var2, var1) ? 1 : 0;
      }

      private boolean pC(ICDoWhileStm var1, int var2) {
         ICIfStm var3 = (ICIfStm)this.sY;
         ICPredicate var4 = var3.getBranchPredicate(var3.size() - 1);
         var3.removeBranch(var3.size() - 1);
         if (var3.size() == 0) {
            this.E.removeLast();
            if (this.ys != null && this.E.isEmpty()) {
               this.ys.setDefaultBlock(null);
            }
         }

         if (var1.getPredicate().isLitteralTrue()) {
            var4.reverse(ais.this.of);
            var1.setPredicate(var4);
         } else {
            var1.setPredicate(
               ais.this.ef.createPredicate(CUtil.andL(ais.this.m, CUtil.notL(ais.this.m, var4.getExpression()), var1.getPredicate().getExpression()))
            );
         }

         return true;
      }

      public boolean pC(int var1) {
         return !((ICIfStm)this.sY).hasDefaultBlock() && var1 == ((ICIfStm)this.sY).size() - 1;
      }
   }
}
