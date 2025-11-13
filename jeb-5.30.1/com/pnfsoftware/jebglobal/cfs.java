package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.HashSet;

public class cfs extends AbstractDOptimizer {
   public cfs() {
      super(DOptimizerType.CUSTOM);
   }

   @Override
   public int perform() {
      ArrayList var1 = new ArrayList();
      HashSet var2 = new HashSet();
      BasicBlock var3 = this.cfg.getLast();

      for (BasicBlock var5 : this.cfg) {
         IDInstruction var6 = (IDInstruction)var5.getLast();
         if (var6.isReturn() && (var5 != var3 || !var1.isEmpty())) {
            var1.add(var5);
            if (var6.getSize() == 1) {
               var2.add(var6.getOffset());
            }
         }
      }

      if (var1.isEmpty()) {
         return 0;
      } else {
         IJavaType var15 = (IJavaType)this.ctx.getParametersTypeMap().get(-1);
         IDVar var16 = null;
         if (!var15.isVoid()) {
            if (!var2.isEmpty()) {
               DUtil.modifyInstructionSizes(this.ctx, var1x -> var2.contains(var1x.getOffset()) ? 2 : null);
            }

            var16 = this.ctx.createVirtualVar(var15);
         }

         IDInstruction var17 = this.ctx.createReturn(var16);
         BasicBlock var7 = new BasicBlock();
         int var8 = (int)this.cfg.getEndAddress();
         var17.setOffset(var8);
         var7.add(var17);
         this.cfg.addBlock(var7);

         for (BasicBlock var10 : var1) {
            IDInstruction var11 = (IDInstruction)var10.getLast();
            Assert.a(var11.isReturn());
            if (var15.isVoid()) {
               var11.transformToJump(var8);
            } else {
               IDExpression var12 = var11.getReturnExpression();
               IDInstruction var13 = this.ctx.createAssign(var16, var12).withSize(var11.getSize() - 1).withOffset(var11.getOffset());
               IDInstruction var14 = this.ctx.createJump(var8).withSize(1).withOffset(var11.getOffsetEnd() - 1L);
               Assert.a(var10.outsize() == 0);
               var10.remove(var10.size() - 1);
               var10.add(var13);
               var10.add(var14);
            }

            this.cfg.addEdge(var10, var7);
         }

         this.cfg.invalidateDataFlowAnalysis();
         DUtil.verifyGraph(this.ctx);
         return var1.size();
      }
   }
}
