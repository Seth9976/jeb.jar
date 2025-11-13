package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class cbh extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cbh.class);
   private IDExpression RF;
   private LinkedHashMap xK = new LinkedHashMap();
   private Integer Dw;

   @Override
   public int perform() {
      int var1 = 0;
      var1 += this.q();
      int var2 = 0;

      while (true) {
         while (true) {
            ArrayList var21;
            while (true) {
               if (var2 >= this.cfg.size()) {
                  if (var1 > 0) {
                     this.cfg.invalidateDataFlowAnalysis();
                  }

                  return var1;
               }

               int var3 = var2++;
               this.RF = null;
               this.xK.clear();
               this.Dw = null;
               HashSet var4 = new HashSet();
               long var5 = -1L;
               IDTryData var7 = this.ctx.getExceptionData();

               while (var3 < this.cfg.size() && var4.add(var3)) {
                  BasicBlock var8 = this.cfg.get(var3);
                  if (this.xK.isEmpty()) {
                     var5 = var8.getBase();
                  } else if (var8.size() > 1 || var8.insize() != 1 || !var7.compareHandlers((int)var8.getBase(), (int)var5)) {
                     break;
                  }

                  IDInstruction var9 = (IDInstruction)var8.getLast();
                  if (!var9.isJcond()) {
                     break;
                  }

                  int var10 = this.q(var3, var9);
                  if (var10 < 0) {
                     break;
                  }

                  var3 = var10;
               }

               if (this.xK.size() >= 2) {
                  this.Dw = (int)this.cfg.get(var3).getBase();
                  Object[] var10000 = new Object[]{this.RF, this.Dw, this.xK.values()};
                  var21 = new ArrayList(this.xK.values());
                  if (var21.size() != 2) {
                     break;
                  }

                  BasicBlock var22 = this.cfg.getBlockAt(var5);
                  BasicBlock var24 = this.cfg.getBlockAt((long)((cbh.eo)var21.get(0)).xK);
                  BasicBlock var11 = this.cfg.getBlockAt((long)((cbh.eo)var21.get(1)).xK);
                  if (!CFGUtil.canReach(var24, var11, false, Arrays.asList(var22)) && !CFGUtil.canReach(var11, var24, false, Arrays.asList(var22))) {
                     break;
                  }
               }
            }

            int var23 = ((cbh.eo)var21.get(0)).q;
            BasicBlock var25 = this.cfg.get(var23);
            int var26 = ((cbh.eo)var21.get(1)).q;
            BasicBlock var12 = this.cfg.get(var26);
            if (var23 + 1 == var26) {
               this.cfg.deleteOutEdges(var25);
               IDInstruction var27 = (IDInstruction)var25.getLast();
               IDSwitchData var28 = this.ctx.createSwitchData();

               for (Entry var33 : this.xK.entrySet()) {
                  var28.addCase(var33.getKey(), this.ctx.createTarget(((cbh.eo)var33.getValue()).xK), false);
               }

               IDInstruction var31 = this.ctx.createSwitch(this.RF, var28);
               var31.copyBaseFields(var27);
               var25.set(var25.size() - 1, var31);
               this.cfg.deleteOutEdges(var12);
               IDInstruction var34 = (IDInstruction)var12.getLast();
               this.cfg.addEdge(var25, var12);

               for (IDTarget var36 : var28.getTargets(true)) {
                  this.cfg.addEdge(var25, this.cfg.getBlockAt((long)var36.getOffset()));
               }

               var34.transformToJump(this.ctx.createTarget(this.Dw));
               this.cfg.addEdge(var12, this.cfg.getBlockAt((long)this.Dw.intValue()));
               break;
            }

            if (((IDInstruction)var25.getLast()).getSize() >= 2) {
               this.cfg.deleteOutEdges(var25);
               IDInstruction var13 = (IDInstruction)var25.getLast();
               var13.adjustSize(-1);
               IDSwitchData var14 = this.ctx.createSwitchData();

               for (Entry var16 : this.xK.entrySet()) {
                  var14.addCase(var16.getKey(), this.ctx.createTarget(((cbh.eo)var16.getValue()).xK), false);
               }

               IDInstruction var29 = this.ctx.createSwitch(this.RF, var14);
               var29.copyBaseFields(var13);
               IDInstruction var32 = this.ctx.createNop();
               var32.setSize(1);
               var32.setOffset(this.cfg.get(var23 + 1).getBase() - 1L);
               var25.add(var32);
               BasicBlock var17 = DUtil.splitBlock(this.ctx, var25, var25.size() - 1);
               var25.set(var25.size() - 1, var29);
               this.cfg.addEdge(var25, var17);

               for (IDTarget var19 : var14.getTargets(true)) {
                  this.cfg.addEdge(var25, this.cfg.getBlockAt((long)var19.getOffset()));
               }

               var32.transformToJump(this.ctx.createTarget(this.Dw));
               this.cfg.addEdge(var17, this.cfg.getBlockAt((long)this.Dw.intValue()));
               break;
            }
         }

         var1++;
         this.cleanGraph();
         var2 = 0;
      }
   }

   private int q(int var1, IDInstruction var2) {
      if (var2.getJcondCondition() instanceof IDOperation var3) {
         JavaOperatorType var11 = var3.getOperator().getOperatorType();
         if (var11 == JavaOperatorType.EQ || var11 == JavaOperatorType.NE) {
            IDExpression var5 = var3.getLeft();
            IDExpression var6 = var3.getRight();
            if (!(var5 instanceof IDImm) && var6 instanceof IDImm var7) {
               if (!var6.getType().isSingleSlot()) {
                  return -1;
               }

               if (this.RF == null) {
                  this.RF = var5;
               } else if (!this.RF.equalsEx(var5, false)) {
                  return -1;
               }

               int var8;
               int var9;
               boolean var10;
               if (var11 == JavaOperatorType.EQ) {
                  var8 = var2.getBranchTarget();
                  var9 = var1 + 1;
                  var10 = false;
               } else {
                  var8 = (int)var2.getOffsetEnd();
                  var9 = this.cfg.getBlockIndex(var2.getBranchTarget());
                  var10 = true;
               }

               this.q(var1, var7, var8, var10);
               return var9;
            }
         }
      }

      return -1;
   }

   private boolean q(int var1, IDImm var2, int var3, boolean var4) {
      int var5 = (int)var2.getRawValue();
      if (this.xK.get(var5) != null) {
         return false;
      } else {
         this.xK.put(var5, new cbh.eo(var1, var2, var3, var4));
         return true;
      }
   }

   int q() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 2 && var3.insize() == 1 && ((IDInstruction)var3.get(1)).isJcond() && ((IDInstruction)var3.get(0)).isAssignToVar()) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            if (var4.getAssignSource() instanceof IDImm) {
               IDVar var5 = var4.getAssignDestination().asVar();
               BasicBlock var6 = var3.getInputBlock(0);
               this.analyzeChains();
               if (!this.dfa.isAlive(var6, 0, var5.getId())) {
                  IDInstruction var7 = (IDInstruction)var6.get(0);
                  DUtil.modifyInstructionSize(this.ctx, var7, 2);
                  long var8 = var7.getOffset();
                  var7.setOffset(var8 + 1L);
                  var7.adjustSize(-1);
                  IDInstruction var10 = var4.duplicateWithOffsetAndSize(var8, 1);
                  var6.add(0, var10);
                  var4.transformToNop();
                  this.dfa.invalidate();
                  var1++;
                  var2--;
               }
            }
         }
      }

      return var1;
   }

   private static class eo {
      int q;
      IDImm RF;
      int xK;
      boolean Dw;

      eo(int var1, IDImm var2, int var3, boolean var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      @Override
      public String toString() {
         String var1 = Strings.ff("@%d:%s->0x%X", this.q, this.RF, this.xK);
         if (this.Dw) {
            var1 = var1 + "[rev]";
         }

         return var1;
      }
   }
}
