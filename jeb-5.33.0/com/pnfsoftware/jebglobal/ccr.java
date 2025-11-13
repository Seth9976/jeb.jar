package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class ccr extends AbstractDOptimizer {
   public ccr() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      return this.pC(false, null);
   }

   public int pC(boolean var1, IDVar var2) {
      if (!var1 || bsg.kS(this.g) && Boolean.TRUE.equals(this.ctx.getData("unvirtualized"))) {
         int var3 = 0;
         TreeSet var4 = new TreeSet();

         label155:
         for (int var5 = 1; var5 < this.cfg.size(); var5++) {
            BasicBlock var6 = this.cfg.get(var5);
            IDInstruction var7;
            if (var6.size() == 1
               && var6.insize() == 2
               && (var7 = (IDInstruction)var6.get(0)).isJcond()
               && var7.getJcondCondition() instanceof IDOperation var10
               && var10.getOperatorType() == JavaOperatorType.LT
               && var10.getOperand1() instanceof IDVar var11
               && var10.getOperand2() instanceof IDImm var12
               && var12.isZero()) {
               BasicBlock var34 = this.cfg.getBlockAt(var6.getEndAddress());
               IDInstruction var9;
               if (var34.size() == 3
                  && var34.insize() == 1
                  && var34.outsize() == 1
                  && var34.getOutputBlock(0) == var6
                  && ((IDInstruction)var34.get(2)).isJump()
                  && (var9 = (IDInstruction)var34.get(1)).isAssignToVar(var11.getId())
                  && var9.getAssignSource() instanceof IDOperation var14
                  && var14.getOperatorType() == JavaOperatorType.SUB
                  && var14.getOperand1() == var11
                  && var14.getOperand2() instanceof IDImm var15
                  && var15.getRawValue() == 1L
                  && (var9 = (IDInstruction)var34.get(0)).isAssign()
                  && var9.getAssignSource() instanceof IDImm var16
                  && var16.isZero()
                  && var9.getAssignDestination() instanceof IDArrayElt var17
                  && var17.getArray() instanceof IDVar var18
                  && (var2 == null || var2 == var18)
                  && var17.getIndex() == var11) {
                  BasicBlock var39;
                  if (var6.getInputBlock(0) != var34) {
                     var39 = var6.getInputBlock(0);
                  } else {
                     var39 = var6.getInputBlock(1);
                  }

                  int var20 = var39.size() - 1;
                  if (((IDInstruction)var39.get(var20)).isJump()) {
                     var20--;
                  }

                  IDInstruction var8;
                  if ((var8 = (IDInstruction)var39.get(var20)).isAssignToVar(var11.getId())
                     && var8.getAssignSource() instanceof IDOperation var21
                     && var21.getOperatorType() == JavaOperatorType.SUB
                     && var21.getOperand1() instanceof IDVar var22
                     && var21.getOperand2() instanceof IDImm var23
                     && var23.getRawValue() == 1L) {
                     this.analyzeChains();
                     Collection var42 = this.dfa.getUseDefs(var8.getOffset(), var22.getId());
                     if (var42.size() >= 2) {
                        for (long var26 : var42) {
                           var7 = (IDInstruction)this.cfg.getInstructionAt(var26);
                           if (var7 == null || !var7.isAssignToVar(var22.getId()) || !(var7.getAssignSource() instanceof IDImm var28)) {
                              continue label155;
                           }

                           int var44 = (int)var28.getRawValue();
                           var4.add(var44);
                        }

                        if (var4.size() >= 2) {
                           int var43 = (Integer)var4.last();
                           if (var43 >= 2 && var43 <= 100 && this.pC(var39, var20, var18, var11, var22, var43, var16)) {
                              this.invalidateChains();
                              var5++;
                              var3++;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var3 > 0) {
            this.cleanGraph();
         }

         return var3;
      } else {
         return 0;
      }
   }

   private boolean pC(BasicBlock var1, int var2, IDVar var3, IDVar var4, IDVar var5, int var6, IDImm var7) {
      int var8 = ((IDInstruction)var1.getOutputBlock(0).get(0)).getBranchTarget();
      int var9 = (int)this.cfg.getEndAddress();
      int var10 = var9;
      ArrayList var11 = new ArrayList();

      for (int var13 = var6 - 1; var13 >= 1; var13--) {
         BasicBlock var12 = this.pC(var11);
         var12.add(this.ctx.createJcond(var10 + 2, this.ctx.createOperation(null, JavaOperatorType.LE, var5, this.ctx.createInt(var13))).withOffset(var10));
         var12 = this.pC(var11);
         var12.add(this.ctx.createAssign(this.ctx.createArrayElt(var3, this.ctx.createInt(var13), var7.getType()), var7.duplicate()).withOffset(var10 + 1));
         var10 += 2;
      }

      BasicBlock var21 = this.pC(var11);
      var21.add(this.ctx.createAssign(this.ctx.createArrayElt(var3, this.ctx.createInt(0), var7.getType()), var7.duplicate()).withOffset(var10));
      var21.add(this.ctx.createAssign(var4, this.ctx.createInt(-1)).withOffset(++var10));
      var21.add(this.ctx.createJump(var8).withOffset(++var10));
      IDInstruction var14 = this.ctx.createJump(var9);
      var14.copyBaseFields((IDInstruction)var1.getLast());
      var1.set(var1.size() - 1, var14);
      this.cfg.deleteOutEdges(var1);
      this.cfg.addEdge(var1, this.cfg.getBlockAt((long)var9));

      for (BasicBlock var16 : var11) {
         IDInstruction var17 = (IDInstruction)var16.getLast();
         if (var17.isJump()) {
            this.cfg.addEdge(var16, this.cfg.getBlockAt((long)var17.getBranchTarget()));
         } else if (var17.isJcond()) {
            this.cfg.addEdge(var16, this.cfg.getBlockAt(var17.getOffsetEnd()));
            this.cfg.addEdge(var16, this.cfg.getBlockAt((long)var17.getBranchTarget()));
         } else {
            if (var17.getBreakingFlow().isBroken()) {
               throw new RuntimeException();
            }

            this.cfg.addEdge(var16, this.cfg.getBlockAt(var17.getOffsetEnd()));
         }
      }

      return true;
   }

   private BasicBlock pC(List var1) {
      BasicBlock var2 = new BasicBlock();
      this.cfg.addBlock(var2);
      var1.add(var2);
      return var2;
   }
}
