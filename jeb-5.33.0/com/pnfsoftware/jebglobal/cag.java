package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.LinkedHashMap;

public class cag extends AbstractDOptimizer {
   static final String pC = ckx.pC(
      new byte[]{
         15,
         5,
         17,
         15,
         19,
         70,
         11,
         9,
         26,
         71,
         7,
         48,
         93,
         82,
         88,
         87,
         94,
         8,
         1,
         30,
         87,
         65,
         71,
         84,
         64,
         83,
         103,
         62,
         11,
         2,
         26,
         4,
         15,
         13,
         15,
         10,
         72,
         64,
         61,
         66,
         3,
         17,
         16,
         84,
         90,
         79,
         60
      },
      2,
      206
   );
   static final String A = ckx.pC(
      new byte[]{-52, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 124, 39, 6, 27, 7, 9, 92, 22, 19, 86, 9, 18, 27, 43, 44, 11, 1, 77, 1, 96}, 1, 128
   );

   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size() - 1; var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         IDInstruction var5 = (IDInstruction)var3.getLast();
         IDVar var4;
         if (var5.isSwitchOnInt()
            && !var5.getSwitchData().isEmptySwitch()
            && var5.getSwitchExpression() instanceof IDCallInfo var6
            && (var4 = pC(var6)) != null) {
            cag.Av var8 = new cag.Av(var2, var3, var4);
            if (var8.pC()) {
               var1++;
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   static IDVar pC(IDCallInfo var0) {
      return A.equals(var0.getMethodSignature()) && var0.getArgument(0) instanceof IDVar var1 ? var1 : null;
   }

   static Integer pC(IDOperation var0, IDVar var1) {
      JavaOperatorType var2 = var0.getOperatorType();
      if (var2 == JavaOperatorType.NE && var0.getLeft() == var1 && var0.getRight().isConstantImm()) {
         IDImm var3 = (IDImm)var0.getRight();
         return (int)var3.getRawValue();
      } else {
         return null;
      }
   }

   static Couple pC(IDExpression var0, IDVar var1, int var2, IDGlobalContext var3) {
      IDCallInfo var4 = null;
      boolean var5 = false;
      if (var0 instanceof IDCallInfo) {
         var4 = (IDCallInfo)var0;
         var5 = true;
      } else if (var0 instanceof IDOperation var6) {
         JavaOperatorType var7 = var6.getOperatorType();
         if (var7 == JavaOperatorType.LOG_NOT && var6.getRight() instanceof IDCallInfo) {
            var4 = (IDCallInfo)var6.getRight();
         } else if (var7 == JavaOperatorType.EQ && var6.getLeft() instanceof IDCallInfo && var6.getRight().isConstantImm(0L)) {
            var4 = (IDCallInfo)var6.getLeft();
         } else if (var7 == JavaOperatorType.NE && var6.getLeft() instanceof IDCallInfo && var6.getRight().isConstantImm(0L)) {
            var4 = (IDCallInfo)var6.getLeft();
            var5 = true;
         }
      }

      if (var4 != null
         && pC.equals(var4.getMethodSignature())
         && var4.getArgument(0).equals(var1)
         && var4.getArgument(1) instanceof IDImm var8
         && var8.isString()) {
         String var10 = var8.getStringValue(var3);
         if (var10 != null && var10.hashCode() == var2) {
            return new Couple(var10, var5);
         }
      }

      return null;
   }

   private class Av {
      int pC;
      BasicBlock A;
      IDVar kS;

      Av(int var2, BasicBlock var3, IDVar var4) {
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
      }

      boolean pC() {
         IDInstruction var1 = (IDInstruction)this.A.getLast();
         IDSwitchData var2 = var1.getSwitchData();
         int var3 = (int)var1.getOffsetEnd();
         int var4 = -1;
         IDInstruction var5 = (IDInstruction)cag.this.cfg.getInstruction((long)var3);
         if (var5 != null && var5.isJump()) {
            var4 = var5.getBranchTarget();
         }

         LinkedHashMap var6 = new LinkedHashMap();

         for (int var8 : var2.getCasesAsInt()) {
            int var9 = var2.getTargetForCase(var8).getOffset();
            IDInstruction var10 = (IDInstruction)cag.this.cfg.getInstructionAt(var9);
            if (var10 != null && var10.isJcond()) {
               Couple var11 = cag.pC(var10.getJcondCondition(), this.kS, var8, cag.this.g);
               if (var11 == null) {
                  return false;
               }

               String var12 = (String)var11.getFirst();
               boolean var13 = (Boolean)var11.getSecond();
               int var14 = !var13 ? (int)var10.getOffsetEnd() : var10.getBranchTarget();
               if (var6.put(var12, var14) != null) {
                  return false;
               }

               int var16 = !var13 ? var10.getBranchTarget() : (int)var10.getOffsetEnd();
               IDInstruction var15;
               if (var16 == var3
                  || var16 == var4
                  || (var15 = (IDInstruction)cag.this.cfg.getInstructionAt(var16)) != null && pC(var15, var3, var4)
                  || var5.isReturnOrThrow() && ((bpv)var5).pC(var15)) {
                  continue;
               }

               return false;
            }

            return false;
         }

         if (var6.isEmpty()) {
            return false;
         } else {
            var2.clear();
            var1.setSwitchExpression(this.kS);
            cag.this.cfg.deleteOutEdges(this.A);
            cag.this.cfg.addEdge(this.A, cag.this.cfg.getBlock(this.pC + 1));

            for (String var18 : var6.keySet()) {
               int var19 = (Integer)var6.get(var18);
               var2.addCase(var18, cag.this.ctx.createTarget(var19), false);
               cag.this.cfg.addEdge(this.A, cag.this.cfg.getBlockAt((long)var19));
            }

            cag.this.cleanGraph();
            return true;
         }
      }

      static boolean pC(IDInstruction var0, int var1, int var2) {
         return var0 != null && var0.isJump() && (var0.getBranchTarget() == var1 || var0.getBranchTarget() == var2);
      }
   }
}
