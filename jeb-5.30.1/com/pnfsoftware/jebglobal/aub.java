package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class aub extends AbstractEOptimizer {
   public aub() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (int var3 = 0; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);
         if (((IEStatement)var4.getLast()).isConditionalJump()) {
            for (int var2 = var3 + 1; var2 < this.cfg.size(); var2++) {
               BasicBlock var5 = this.cfg.get(var2);
               if (var5.size() != 1 || !((IEStatement)var5.get(0)).isConditionalJump() || var5.insize() != 1) {
                  if (var2 - var3 >= 2 && this.q(var3, var2)) {
                     this.cfg.invalidateDataFlowAnalysis();
                     amw.q(this.cfg);
                     var1++;
                  }

                  var3 = var2 - 1;
                  break;
               }
            }
         }
      }

      return this.postPerform(var1);
   }

   private boolean q(int var1, int var2) {
      IEVar var3 = null;

      for (int var4 = var1; var4 < var2; var4++) {
         BasicBlock var5 = this.cfg.get(var4);
         IEJump var6 = ((IEStatement)var5.getLast()).asJump();
         IEGeneric var7 = var6.getCondition();
         Set var8 = EUtil.collectVars(var7);
         if (var8.size() != 1) {
            return false;
         }

         IEVar var9 = (IEVar)var8.iterator().next();
         if (var3 == null) {
            var3 = var9;
         } else if (var3 != var9) {
            return false;
         }
      }

      if (var3 == null) {
         return false;
      } else {
         IDFA var17 = this.cfg.doDataFlowAnalysis();
         HashSet var18 = new HashSet();
         HashSet var19 = new HashSet();

         for (int var20 = var1; var20 < var2; var20++) {
            BasicBlock var22 = this.cfg.get(var20);
            AddressableInstruction var24 = var22.getLast2();
            long var10 = var24.getOffset();

            for (long var14 : var17.getUseDefs(var10, var3.getId())) {
               if (var14 == -1L) {
                  return false;
               }

               if (var19.add(var14)) {
                  IEStatement var16 = (IEStatement)this.cfg.getInstruction(var14);
                  if (!this.q(var16, var3, var18)) {
                     return false;
                  }
               }
            }
         }

         int var21 = 0;
         LinkedHashMap var23 = new LinkedHashMap();

         for (int var25 = var1; var25 < var2; var25++) {
            BasicBlock var27 = this.cfg.get(var25);
            IEJump var11 = ((IEStatement)var27.getLast()).asJump();
            IEGeneric var30 = var11.getCondition();
            int var32 = this.q(var30, var3, var18);
            if (var32 < 0) {
               return false;
            }

            if (var32 == 0) {
               IENop var34 = this.ectx.createNop(var11);
               var23.put(var25, var34);
            } else if (var32 == 1) {
               var11.setCondition(null);
               this.cfg.deleteEdge(var27, this.cfg.get(var25 + 1));
               var21++;
            } else if (var32 != 2) {
               throw new RuntimeException();
            }
         }

         for (Integer var28 : var23.keySet()) {
            IEStatement var29 = (IEStatement)var23.get(var28);
            BasicBlock var31 = this.cfg.get(var28);
            IEJump var33 = (IEJump)var31.set(var31.size() - 1, var29);
            this.cfg.deleteEdge(var31, this.cfg.getBlockAt((long)var33.getBranchAddress()));
            var21++;
         }

         return var21 > 0;
      }
   }

   private boolean q(IEStatement var1, IEVar var2, Set var3) {
      if (!(var1 instanceof IEAssign)) {
         return false;
      } else {
         IEAssign var4 = var1.asAssign();
         if (var4.getDstOperand() != var2) {
            return false;
         } else {
            IEGeneric var5 = var4.getRightOperand();
            if (var5 instanceof IEImm) {
               var3.add((IEImm)var5);
               return true;
            } else if (var5 instanceof IECond var6 && var6.getExpressionTrue() instanceof IEImm && var6.getExpressionFalse() instanceof IEImm) {
               var3.add((IEImm)var6.getExpressionTrue());
               var3.add((IEImm)var6.getExpressionFalse());
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private int q(IEGeneric var1, IEVar var2, Set var3) {
      if (var1.examine(var0 -> var0.isMem())) {
         return -1;
      } else {
         byte var4 = 0;
         Iterator var5 = var3.iterator();

         while (var5.hasNext()) {
            IEImm var6 = (IEImm)var5.next();
            EState var7 = this.ectx.buildEmptyState();
            var7.setMemory(null);
            var7.setValue(var2, var6);

            try {
               IEImm var8 = var1.evaluate(var7);
               if (var8.isZero()) {
                  var4 |= 1;
               } else {
                  var4 |= 2;
                  var5.remove();
               }
            } catch (EvaluationException var9) {
               return -1;
            }
         }

         if (var4 == 1) {
            return 0;
         } else if (var4 == 2) {
            return 1;
         } else {
            return var4 == 3 ? 2 : -1;
         }
      }
   }
}
