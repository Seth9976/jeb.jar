package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStackManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class aof extends AbstractEOptimizer {
   public aof() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (IEStatement var5 : var3) {
            if (var5 instanceof IEAssign) {
               IEAssign var6 = var5.asAssign();
               if (var6.getDstOperand() instanceof IEMem) {
                  IEMem var7 = var6.getDstOperand().asMem();
                  int var8 = var7.getBitsize();
                  if (var7.getReference() instanceof IEVar && var7.getReference().asVar().isStackReference()) {
                     IEVar var9 = var7.getReference().asVar();
                     int var10 = var9.getAddress().intValue();
                     IEVar var11 = this.ectx.getStackVariable(var10);
                     if (var11 != null) {
                        int var12 = var11.getBitsize();
                        if (var12 < var8 && var8 % var12 == 0) {
                           int var13 = var8 / var12;
                           ArrayList var14 = new ArrayList(var13);
                           var14.add(var11);
                           int var15 = var10 + var12 / 8;

                           for (int var16 = var10 + var8 / 8; var15 < var16; var15 += var12 / 8) {
                              var11 = this.ectx.getStackVariable(var15);
                              if (var11 == null || var11.getBitsize() != var12) {
                                 var14 = null;
                                 break;
                              }

                              var14.add(var11);
                           }

                           if (var14 != null) {
                              if (this.ectx.getGlobalContext().isBigEndian()) {
                                 Collections.reverse(var14);
                              }

                              if (new aof.Av(var5, var10, var8, var14).pC()) {
                                 var1++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   class Av {
      IEStatement pC;
      int A;
      int kS;
      List wS;
      int UT;
      List E;
      List sY = new ArrayList();
      List ys = new ArrayList();

      Av(IEStatement var2, int var3, int var4, List var5) {
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
         this.wS = var5;
         this.UT = var5.size();
         this.E = new ArrayList(this.UT);
         var5.forEach(var1x -> {
            IEVar var2x = aof.this.ectx.getStackReference(var1x.getAddress());
            if (var2x != null) {
               this.E.add(var2x);
            }
         });
      }

      boolean pC() {
         aof.Av.Av var1 = new aof.Av.Av();

         for (IEStatement var3 : aof.this.cfg.instructions()) {
            if (!var3.visitInstruction(var1)) {
               return false;
            }
         }

         if (this.sY.isEmpty()) {
            return false;
         } else {
            IEStackManager var11 = aof.this.ectx.getStackManager();
            int var12 = this.A + this.kS / 8;
            if (!var11.canClearNativeStack(this.A, var12)) {
               return false;
            } else {
               var11.clearNativeStack(this.A, var12);

               for (IEVar var5 : this.wS) {
                  var11.removeVariable(var5);
               }

               for (IEVar var15 : this.E) {
                  aof.this.ectx.removeStackReference(var15.getAddress());
               }

               IEVar var14 = var11.createStackItem(this.A, this.kS);
               Assert.a(var14 != null && var14.getAddress() == this.A && var14.getBitsize() == this.kS);
               IEVar var16 = aof.this.ectx.createStackReference(this.A);
               Assert.a(var16 != null);

               for (Couple var7 : this.sY) {
                  ((IEGeneric)var7.getSecond()).replaceSubExpression((IEGeneric)var7.getFirst(), var14);
               }

               for (Couple var18 : this.ys) {
                  IEVar var8 = (IEVar)var18.getFirst();
                  int var9 = (int)(var8.getAddress() - this.A);
                  Object var10 = var9 == 0 ? var16 : EUtil.add(var16, EUtil.imm(var9, var16.getBitsize()));
                  ((IEGeneric)var18.getSecond()).replaceSubExpression(var8, (IEGeneric)var10);
               }

               return true;
            }
         }
      }

      private class Av implements IEVisitor {
         public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
            if (var1 instanceof IECompose && ((IECompose)var1).getPartsCount() == Av.this.UT) {
               List var4 = ((IECompose)var1).getParts();
               if (var4.equals(Av.this.wS)) {
                  Av.this.sY.add(new Couple(var1, var2));
                  var3.skipChildren();
                  return;
               }
            }

            if (var1 instanceof IEVar) {
               if (Av.this.wS.contains(var1)) {
                  var3.interrupt(false);
                  return;
               }

               if (Av.this.E.contains(var1)) {
                  Av.this.ys.add(new Couple((IEVar)var1, var2));
               }
            }
         }
      }
   }
}
