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

public class aqs extends AbstractEOptimizer {
   public aqs() {
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

                              if (new aqs.eo(var5, var10, var8, var14).q()) {
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

   class eo {
      IEStatement q;
      int RF;
      int xK;
      List Dw;
      int Uv;
      List oW;
      List gO = new ArrayList();
      List nf = new ArrayList();

      eo(IEStatement var2, int var3, int var4, List var5) {
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
         this.Dw = var5;
         this.Uv = var5.size();
         this.oW = new ArrayList(this.Uv);
         var5.forEach(var1x -> {
            IEVar var2x = aqs.this.ectx.getStackReference(var1x.getAddress());
            if (var2x != null) {
               this.oW.add(var2x);
            }
         });
      }

      boolean q() {
         aqs.eo.eo var1 = new aqs.eo.eo();

         for (IEStatement var3 : aqs.this.cfg.instructions()) {
            if (!var3.visitInstruction(var1)) {
               return false;
            }
         }

         if (this.gO.isEmpty()) {
            return false;
         } else {
            IEStackManager var11 = aqs.this.ectx.getStackManager();
            int var12 = this.RF + this.xK / 8;
            if (!var11.canClearNativeStack(this.RF, var12)) {
               return false;
            } else {
               var11.clearNativeStack(this.RF, var12);

               for (IEVar var5 : this.Dw) {
                  var11.removeVariable(var5);
               }

               for (IEVar var15 : this.oW) {
                  aqs.this.ectx.removeStackReference(var15.getAddress());
               }

               IEVar var14 = var11.createStackItem(this.RF, this.xK);
               Assert.a(var14 != null && var14.getAddress() == this.RF && var14.getBitsize() == this.xK);
               IEVar var16 = aqs.this.ectx.createStackReference(this.RF);
               Assert.a(var16 != null);

               for (Couple var7 : this.gO) {
                  ((IEGeneric)var7.getSecond()).replaceSubExpression((IEGeneric)var7.getFirst(), var14);
               }

               for (Couple var18 : this.nf) {
                  IEVar var8 = (IEVar)var18.getFirst();
                  int var9 = (int)(var8.getAddress() - this.RF);
                  Object var10 = var9 == 0 ? var16 : EUtil.add(var16, EUtil.imm(var9, var16.getBitsize()));
                  ((IEGeneric)var18.getSecond()).replaceSubExpression(var8, (IEGeneric)var10);
               }

               return true;
            }
         }
      }

      private class eo implements IEVisitor {
         public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
            if (var1 instanceof IECompose && ((IECompose)var1).getPartsCount() == eo.this.Uv) {
               List var4 = ((IECompose)var1).getParts();
               if (var4.equals(eo.this.Dw)) {
                  eo.this.gO.add(new Couple(var1, var2));
                  var3.skipChildren();
                  return;
               }
            }

            if (var1 instanceof IEVar) {
               if (eo.this.Dw.contains(var1)) {
                  var3.interrupt(false);
                  return;
               }

               if (eo.this.oW.contains(var1)) {
                  eo.this.nf.add(new Couple((IEVar)var1, var2));
               }
            }
         }
      }
   }
}
