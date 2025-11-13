package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class bxl extends AbstractDOptimizer {
   public bxl() {
      super(DOptimizerType.NORMAL);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.isInvoke()
               && var5.getInvokeData() instanceof IDCallInfo var6
               && var6.getInvokeType() == DInvokeType.DIRECT
               && var6.getMethodName().equals("<init>")) {
               IDExpression var13 = var6.getArgument(0);
               IDVar var8 = null;
               if (var13 instanceof IDVar var9) {
                  var8 = var9;
               } else if (var13 instanceof IDOperation var10 && var10.isCast() && var10.getRight() instanceof IDVar var11) {
                  var8 = var11;
               }

               if (var8 != null) {
                  bxl.Av var14 = new bxl.Av();
                  var1 += var14.pC(var5, var8);
               }
            }
         }
      }

      return var1;
   }

   class Av {
      Set pC;
      Set A = new HashSet();

      int pC(IDInstruction var1, IDVar var2) {
         IDInstruction var3 = var1;
         bxl.this.analyzeChains();
         bsj var5 = new bsj(var1.getOffset(), var2.getId(), true);
         ArrayDeque var6 = new ArrayDeque();
         var6.add(var5);
         HashSet var7 = new HashSet();
         IDInstruction var8 = null;

         while (!var6.isEmpty()) {
            var5 = (bsj)var6.remove();
            if (var7.add(var5.kS())) {
               IDInstruction var9 = (IDInstruction)bxl.this.cfg.getInstruction(var5.kS());
               if (var9 == null) {
                  return 0;
               }

               IDVar var4 = bxl.this.ctx.getVar(var5.wS());
               if (var9 != var1) {
                  if (!var9.isAssignToVar(var4.getId())) {
                     return 0;
                  }

                  IDExpression var10 = var9.getAssignSource();
                  if (!(var10 instanceof IDVar var11)) {
                     if (!(var10 instanceof IDAllocObjectInfo)) {
                        return 0;
                     }

                     if (var8 == null) {
                        var8 = var9;
                     } else if (var9 != var8) {
                        return 0;
                     }
                     continue;
                  }

                  var3 = var9;
                  var4 = var11;
               }

               for (long var12 : bxl.this.dfa.getUseDefs(var3.getOffset(), var4.getId())) {
                  var6.add(new bsj(var12, var4.getId()));
               }
            }
         }

         if (var8 == null) {
            return 0;
         } else if (!this.pC(var8, var1, var2)) {
            return 0;
         } else {
            ArrayList var24 = new ArrayList();
            var24.add(var1);
            var24.addAll(this.A);

            for (IDInstruction var29 : var24) {
               IDVar var31 = pC(var29);
               if (var31 == null || !this.pC.contains(var31)) {
                  return 0;
               }
            }

            TreeSet var27 = new TreeSet(this.pC);
            int var30 = var27.size() - 1;

            for (IDInstruction var13 : var24) {
               Couple var14 = bxl.this.cfg.getInstructionLocation(var13.getOffset());
               BasicBlock var15 = (BasicBlock)var14.getFirst();
               int var16 = (Integer)var14.getSecond();
               IDVar var17 = pC(var13);
               DUtil.modifyInstructionSize(bxl.this.ctx, var13, 1 + var30);
               var13.adjustSize(-var30);
               long var18 = var13.getOffsetEnd();

               for (IDVar var21 : var27) {
                  if (var21 != var17) {
                     IDInstruction var22 = bxl.this.ctx.createAssign(var21, var17).withOffset(var18);
                     var15.add(++var16, var22);
                     var18++;
                  }
               }

               IDNewInfo var33 = bxk.pC(bxl.this.g, (IDAllocObjectInfo)var8.getAssignSource(), (IDCallInfo)var13.getInvokeData());
               var13.morph(DOpcodeType.IR_ASSIGN, var2, var33);
            }

            var8.setAssignSource(bxl.this.g.createNull());
            bxl.this.invalidateChains();
            return var24.size();
         }
      }

      boolean pC(IDInstruction var1, IDInstruction var2, IDVar var3) {
         IDVar var5 = (IDVar)var1.getAssignDestination();
         HashSet var6 = new HashSet();
         var6.add(var5);
         Couple var7 = bxl.this.cfg.getInstructionLocation(var1.getOffset());
         BasicBlock var8 = (BasicBlock)var7.getFirst();
         int var9 = (Integer)var7.getSecond() + 1;
         HashMap var10 = new HashMap();
         var10.put(var8.getBase(), var6);
         int var11 = 0;
         ArrayDeque var12 = new ArrayDeque();
         var12.add(var8);
         HashSet var13 = new HashSet();

         label101:
         while (!var12.isEmpty()) {
            var8 = (BasicBlock)var12.remove();
            if (var13.add(var8)) {
               if (var11++ > 0) {
                  var9 = 0;
                  var6 = null;

                  for (BasicBlock var15 : var8.getInputs()) {
                     HashSet var16 = (HashSet)var10.get(var15.getBase());
                     if (var16 != null) {
                        if (var6 == null) {
                           var6 = var16;
                        } else if (!var6.equals(var16)) {
                           return false;
                        }
                     }
                  }

                  if (var6 == null) {
                     return false;
                  }

                  var6 = new HashSet(var6);
                  var10.put(var8.getBase(), var6);
               }

               for (; var9 < var8.size(); var9++) {
                  IDInstruction var20 = (IDInstruction)var8.get(var9);
                  if (var20 == var2) {
                     this.pC = var6;
                     continue label101;
                  }

                  if (var20.isInvoke()
                     && var20.getInvokeData() instanceof IDCallInfo var21
                     && var21.getInvokeType() == DInvokeType.DIRECT
                     && var21.getMethodName().equals("<init>")) {
                     IDVar var25 = pC(var21);
                     if (var25 == null || !var6.contains(var25)) {
                        return false;
                     }

                     this.A.add(var20);
                     continue label101;
                  }

                  if (var20.isAssignToVar()) {
                     IDVar var22 = (IDVar)var20.getAssignDestination();
                     if (var6.contains(var22)) {
                        return false;
                     }

                     if (var20.getAssignSource() instanceof IDVar var24 && var6.contains(var24)) {
                        var6.add(var22);
                     } else if (CollectionUtil.hasIntersection(var20.getUsedVariables(), var6)) {
                        return false;
                     }
                  } else if (CollectionUtil.hasIntersection(var20.getUsedVariables(), var6)) {
                     return false;
                  }
               }

               var12.addAll(var8.getOutputs());
            }
         }

         return this.pC != null;
      }

      static IDVar pC(IDInstruction var0) {
         return var0.isInvoke()
               && var0.getInvokeData() instanceof IDCallInfo var1
               && var1.getInvokeType() == DInvokeType.DIRECT
               && var1.getMethodName().equals("<init>")
            ? pC(var1)
            : null;
      }

      static IDVar pC(IDCallInfo var0) {
         IDExpression var1 = var0.getArgument(0);
         if (var1 instanceof IDVar var2) {
            return var2;
         } else {
            return var1 instanceof IDOperation var3 && var3.isCast() && var3.getRight() instanceof IDVar var4 ? var4 : null;
         }
      }
   }
}
