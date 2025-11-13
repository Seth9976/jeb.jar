package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;

public class cbg extends AbstractDOptimizer {
   public cbg() {
      this.addTag("slow");
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         IDInstruction var4;
         if (var3.outsize() == 2 && var3.size() >= 2 && (var4 = (IDInstruction)var3.getLast()).isJcond()) {
            IDInstruction var5 = (IDInstruction)var3.get(var3.size() - 2);
            if (var5.isAssignToVar()) {
               IDVar var6 = var5.getAssignDestination().asVar();
               BasicBlock var7 = this.cfg.getBlockAt(var4.getOffsetEnd());
               BasicBlock var8 = this.cfg.getBlockAt((long)var4.getBranchTarget());
               byte var9;
               BasicBlock var10;
               BasicBlock var11;
               if (CFGUtil.canReach(var7, var3)) {
                  var10 = var7;
                  var11 = var8;
                  var9 = 1;
               } else {
                  if (!CFGUtil.canReach(var8, var3) || var7.size() != 1 || var7.insize() != 1 || !((IDInstruction)var7.get(0)).isJump()) {
                     continue;
                  }

                  var10 = var8;
                  var11 = var7.getOutputBlock(0);
                  var9 = 2;
               }

               if (!bto.q(this.cfg, var3, var11)) {
                  this.analyzeChains();
                  boolean var12 = !this.dfa.getLiveChains(var10, 0, var6.getId(), 1).isEmpty();
                  if (!var12) {
                     boolean var13 = !this.dfa.getLiveChains(var11, 0, var6.getId(), 1).isEmpty();
                     if (var13 && this.dfa.getReachChains(var11, 0, var6.getId(), 2).size() > 1) {
                        IDVar var14 = this.ctx.createVirtualVar(var6.getType());
                        var5.setAssignDestination(var14);
                        var4.replaceUsedVariable(var6, var14);
                        if (var9 == 1) {
                           DUtil.modifyInstructionSize(this.ctx, var4, 3);
                           var4.adjustSize(-2);
                           long var15 = var4.getOffsetEnd();
                           BasicBlock var17 = new BasicBlock();
                           var17.add(this.ctx.createAssign(var6, var14).withOffset(var15));
                           var17.add(this.ctx.createJump((int)var8.getBase()).withOffset(var15 + 1L));
                           var4.setBranchTarget((int)var7.getBase());
                           var4.reverseJcondCondition();
                           this.cfg.deleteOutEdges(var3);
                           this.cfg.addBlock(var2 + 1, var17);
                           this.cfg.addEdge(var3, var17);
                           this.cfg.addEdge(var3, var7);
                           this.cfg.addEdge(var17, var8);
                        } else {
                           if (var9 != 2) {
                              throw new RuntimeException();
                           }

                           IDInstruction var18 = (IDInstruction)var7.get(0);
                           DUtil.modifyInstructionSize(this.ctx, var18, 2);
                           var18.adjustSize(-1);
                           long var16 = var18.getOffset();
                           var18.setOffset(var16 + 1L);
                           var7.add(0, this.ctx.createAssign(var6, var14).withOffset(var16));
                        }

                        this.cfg.invalidateDataFlowAnalysis();
                        var1++;
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
