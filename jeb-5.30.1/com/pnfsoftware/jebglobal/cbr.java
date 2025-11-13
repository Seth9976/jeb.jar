package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class cbr extends AbstractDOptimizer {
   public cbr() {
      this.addTag("deobfuscator");
      bto.Dw(this);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);

         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.isAssignToVar() && var5.getAssignSource().isConstantImm()) {
               IDVar var6 = var5.getAssignDestination().asVar();
               IDImm var7 = var5.getAssignSource().asImm();
               if (!var7.isString() && var3.outsize() == 1 && new cbr.eo(var3, var4 + 1, var6, var7).q()) {
                  this.cleanGraph();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private class eo {
      final int q = 4;
      final int RF = 50;
      final int xK = 2;
      final int Dw = 10;
      BasicBlock Uv;
      int oW;
      IDVar gO;
      IDImm nf;
      private IDState za;
      private Map lm = new HashMap();

      eo(BasicBlock var2, int var3, IDVar var4, IDImm var5) {
         this.Uv = var2;
         this.oW = var3;
         this.gO = var4;
         this.nf = var5;
      }

      boolean q() {
         boolean var1;
         try {
            var1 = this.RF();
         } finally {
            if (this.za != null) {
               this.za.dispose();
            }
         }

         return var1;
      }

      private boolean RF() {
         Assert.a(this.Uv.outsize() == 1);
         BasicBlock var1 = this.Uv;
         int var2 = this.oW;
         if (var2 == var1.size()) {
            if (((IDInstruction)var1.getLast()).getBreakingFlow().isBroken()) {
               return false;
            }

            var2 = 0;
            var1 = var1.getOutputBlock(0);
         }

         long var3 = var1.getAddressOfInstruction(var2);
         int var5 = 0;
         HashSet var6 = new HashSet();
         ReferenceCounter var7 = new ReferenceCounter();

         while (true) {
            Couple var8 = cbr.this.cfg.getInstructionLocation(var3);
            if (var8 == null) {
               return false;
            }

            var1 = (BasicBlock)var8.getFirst();
            var2 = (Integer)var8.getSecond();
            if (var7.inc(var3) > 10) {
               return false;
            }

            IDInstruction var9 = (IDInstruction)var1.get(var2);
            if (this.q(var1, var2)) {
               if (this.za == null) {
                  this.xK();
               }

               try {
                  Assert.a(var3 == var9.getOffset());
                  this.za.setPC((int)var3);
                  var9.evaluate(this.za);
               } catch (DexDecEvaluationException var21) {
                  return false;
               }

               var5++;
               var6.add(var1);
               Integer var10 = this.za.getCurrentFrame().getNextPC();
               if (var10 != null) {
                  var3 = var10.intValue();
                  continue;
               }
            }

            if (var5 >= 4 && var6.size() >= 2) {
               try {
                  var24 = this.za.getVariable(this.gO.getId());
               } catch (DexDecEvaluationException var20) {
                  return false;
               }

               long var25 = this.Uv.getOutputBlock(0).getBase();
               long var12 = var1.getBase();
               bwe var14 = new bwe(cbr.this.cfg, var25, var12);
               var14.q(true);
               if (!var14.xK()) {
                  return false;
               }

               if (this.oW < this.Uv.size()) {
                  DUtil.splitBlock(cbr.this.ctx, this.Uv, this.oW);
               }

               Assert.a(this.oW == this.Uv.size());
               IDInstruction var15 = (IDInstruction)this.Uv.getLast();
               DUtil.modifyInstructionSize(cbr.this.ctx, var15, 3);
               var15.adjustSize(-2);
               long var16 = var15.getOffsetEnd();
               IDInstruction var18 = cbr.this.ctx.createAssign(this.gO, var24.duplicate()).withOffset(var16);
               this.Uv.add(var18);
               IDInstruction var19 = cbr.this.ctx.createJump((int)var1.getBase()).withOffset(var16 + 1L);
               this.Uv.add(var19);
               cbr.this.cfg.reconnectEdge(this.Uv, this.Uv.getOutputBlock(0), var1);
               return true;
            }

            return false;
         }
      }

      private void xK() {
         if (this.za != null) {
            throw new IllegalStateException();
         } else {
            IDState var1 = cbr.this.ctx.getGlobalContext().createState();
            var1.setSubRoutineInvocationPolicy(0);
            var1.setFieldAccessPolicy(0);
            var1.enableEmulator(true);
            var1.enableSandbox(false);
            var1.setMaxIterationCount(50);
            var1.pushContext("context");
            IDEmuFrame var2 = var1.pushFrame(cbr.this.ctx.getMethodSignature());
            var2.getVarMap().put(this.gO.getId(), this.nf);
            this.za = var1;
         }
      }

      private boolean q(BasicBlock var1, int var2) {
         Integer var4 = (Integer)this.lm.get(var1);
         int var3;
         if (var4 != null) {
            if (var4 == -1) {
               return false;
            }

            if (var4 <= var2) {
               return true;
            }

            var3 = var4;
         } else {
            var3 = var1.size();
         }

         this.lm.put(var1, -1);

         for (int var5 = var2; var5 < var3; var5++) {
            IDInstruction var6 = (IDInstruction)var1.get(var5);
            if (var1.outsize() == 0) {
               return false;
            }

            IDVar var7 = var6.getDefinedVariable();
            if (var7 != null && var7 != this.gO) {
               return false;
            }

            for (IDVar var10 : var6.getUsedVariables()) {
               if (var10 != this.gO) {
                  return false;
               }
            }

            if (var6.isAssign() && var6.getAssignDestination() != this.gO) {
               return false;
            }

            if (var6.hasUseSideEffects(true)) {
               return false;
            }

            if (var6.findByType(IDInvokeInfo.class) != null) {
               return false;
            }

            if (var6.findByType(IDField.class) != null) {
               return false;
            }
         }

         this.lm.put(var1, var2);
         return true;
      }
   }
}
