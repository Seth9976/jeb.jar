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

public class arn extends AbstractEOptimizer {
   public arn() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IEAssign && pC(((IEAssign)var5).getDstOperand()) != null) {
               arn.Av var6 = new arn.Av(var3, var4);
               if (var6.pC()) {
                  var4 = var6.A();
                  var1++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private static IEVar pC(IEGeneric var0) {
      if (!(var0 instanceof IESlice)) {
         return null;
      } else {
         IEGeneric var1 = var0.asSlice().getWholeExpression();
         return !(var1 instanceof IEVar) ? null : var1.asVar();
      }
   }

   private class Av {
      BasicBlock pC;
      int A;
      int kS;
      IEVar wS;
      IntGauge UT;
      TreeMap E = new TreeMap();
      IEGeneric sY;

      Av(BasicBlock var2, int var3) {
         this.pC = var2;
         this.A = var3;
      }

      boolean pC() {
         for (int var1 = this.A; var1 < this.pC.size(); var1++) {
            if (!this.pC((IEStatement)this.pC.get(var1))) {
               return false;
            }

            if (this.UT.isComplete()) {
               this.kS = var1 + 1;
               break;
            }
         }

         if (this.kS < 0) {
            return false;
         } else if (this.kS - this.A < 2) {
            return false;
         } else {
            this.sY = arn.this.ectx.createCompose(this.E.values());
            return true;
         }
      }

      int A() {
         Assert.a(this.sY != null);

         for (int var1 = this.A; var1 < this.kS; var1++) {
            IEStatement var2 = (IEStatement)this.pC.get(var1);
            this.pC.set(var1, arn.this.ectx.createNop(var2));
         }

         IEAssign var3 = arn.this.ectx.createAssign(this.wS, this.sY);
         var3.copyProperties((IEStatement)this.pC.get(this.kS - 1));
         this.pC.set(this.kS - 1, var3);
         return this.kS;
      }

      private boolean pC(IEStatement var1) {
         if (!(var1 instanceof IEAssign)) {
            return false;
         } else {
            IEAssign var2 = var1.asAssign();
            IEGeneric var3 = var2.getDstOperand();
            IEVar var4 = arn.pC(var3);
            if (var4 == null) {
               return false;
            } else if (this.wS != null && var4 != this.wS) {
               return false;
            } else {
               IEGeneric var5 = var2.getSrcOperand();
               if (!this.pC(var5)) {
                  return false;
               } else {
                  if (this.wS == null) {
                     this.wS = var4;
                     this.UT = new IntGauge(this.wS.getBitsize());
                  }

                  IERange var6 = var3.asSlice().getRange();
                  if (!this.UT.record(var6.getBegin(), var6.getEnd())) {
                     return false;
                  } else {
                     this.E.put(var6.getBegin(), var5);
                     return true;
                  }
               }
            }
         }
      }

      private boolean pC(IEGeneric var1) {
         if (var1 instanceof IEImm) {
            return true;
         } else if (var1 instanceof IEVar) {
            return var1 != this.wS;
         } else if (var1 instanceof IESlice) {
            IEGeneric var2 = var1.asSlice().getWholeExpression();
            return this.pC(var2);
         } else {
            return false;
         }
      }
   }
}
