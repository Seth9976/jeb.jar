package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.IntGauge;
import java.util.TreeMap;

public class aue extends AbstractEOptimizer {
   public aue() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IEAssign && q(((IEAssign)var5).getDstOperand()) != null) {
               aue.eo var6 = new aue.eo(var3, var4);
               if (var6.q()) {
                  var4 = var6.RF();
                  var1++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private static IEVar q(IEGeneric var0) {
      if (!(var0 instanceof IESlice)) {
         return null;
      } else {
         IEGeneric var1 = var0.asSlice().getWholeExpression();
         return !(var1 instanceof IEVar) ? null : var1.asVar();
      }
   }

   private class eo {
      BasicBlock q;
      int RF;
      int xK;
      IEVar Dw;
      IntGauge Uv;
      TreeMap oW = new TreeMap();
      IEGeneric gO;

      eo(BasicBlock var2, int var3) {
         this.q = var2;
         this.RF = var3;
      }

      boolean q() {
         for (int var1 = this.RF; var1 < this.q.size(); var1++) {
            if (!this.q((IEStatement)this.q.get(var1))) {
               return false;
            }

            if (this.Uv.isComplete()) {
               this.xK = var1 + 1;
               break;
            }
         }

         if (this.xK < 0) {
            return false;
         } else if (this.xK - this.RF < 2) {
            return false;
         } else {
            this.gO = aue.this.ectx.createCompose(this.oW.values());
            return true;
         }
      }

      int RF() {
         Assert.a(this.gO != null);

         for (int var1 = this.RF; var1 < this.xK; var1++) {
            IEStatement var2 = (IEStatement)this.q.get(var1);
            this.q.set(var1, aue.this.ectx.createNop(var2));
         }

         IEAssign var3 = aue.this.ectx.createAssign(this.Dw, this.gO);
         var3.copyProperties((IEStatement)this.q.get(this.xK - 1));
         this.q.set(this.xK - 1, var3);
         return this.xK;
      }

      private boolean q(IEStatement var1) {
         if (!(var1 instanceof IEAssign)) {
            return false;
         } else {
            IEAssign var2 = var1.asAssign();
            IEGeneric var3 = var2.getDstOperand();
            IEVar var4 = aue.q(var3);
            if (var4 == null) {
               return false;
            } else if (this.Dw != null && var4 != this.Dw) {
               return false;
            } else {
               IEGeneric var5 = var2.getSrcOperand();
               if (!this.q(var5)) {
                  return false;
               } else {
                  if (this.Dw == null) {
                     this.Dw = var4;
                     this.Uv = new IntGauge(this.Dw.getBitsize());
                  }

                  IERange var6 = var3.asSlice().getRange();
                  if (!this.Uv.record(var6.getBegin(), var6.getEnd())) {
                     return false;
                  } else {
                     this.oW.put(var6.getBegin(), var5);
                     return true;
                  }
               }
            }
         }
      }

      private boolean q(IEGeneric var1) {
         if (var1 instanceof IEImm) {
            return true;
         } else if (var1 instanceof IEVar) {
            return var1 != this.Dw;
         } else if (var1 instanceof IESlice) {
            IEGeneric var2 = var1.asSlice().getWholeExpression();
            return this.q(var2);
         } else {
            return false;
         }
      }
   }
}
