package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ccv extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = this.q(var3, null);
         if (var4 >= 0 && this.q(var3, var4)) {
            var1++;
         }
      }

      return var1;
   }

   private int q(BasicBlock var1, IDNewArrayInfo var2) {
      if (var1.outsize() == 1) {
         for (int var3 = 0; var3 < var1.size(); var3++) {
            IDInstruction var4 = (IDInstruction)var1.get(var3);
            if (var4.isAssignToVar()
               && var4.getAssignSource() instanceof IDNewArrayInfo var5
               && var5.getCountOfInitialValues() == 0
               && var5.getSize() instanceof IDImm var6
               && var6.getRawValue() < 1000L
               && (var2 == null || var2.equals(var5))) {
               return var3;
            }

            if (var1.irroutsize() > 0 && var4.canThrow()) {
               return -1;
            }
         }
      }

      return -1;
   }

   private boolean q(BasicBlock var1, int var2) {
      IDInstruction var3 = (IDInstruction)var1.get(var2);
      IDNewArrayInfo var4 = var3.getAssignSource().asNewArrayInfo();
      ArrayList var5 = new ArrayList();
      var5.add(new ccv.eo(var1, var2));

      for (BasicBlock var7 : var1.getOutputBlock(0).getInputs()) {
         if (var7 != var1) {
            int var8 = this.q(var7, var4);
            if (var8 < 0) {
               return false;
            }

            var5.add(new ccv.eo(var7, var8));
         }
      }

      if (var5.size() < 2) {
         return false;
      } else {
         HashSet var17 = null;

         for (ccv.eo var20 : var5) {
            IDInstruction var9 = (IDInstruction)var20.q.get(var20.RF);
            IDVar var10 = var9.getAssignDestination().asVar();
            HashSet var11 = new HashSet();
            ccv.CU var12 = new ccv.CU(var10, var11);

            for (int var13 = var20.RF + 1; var13 < var20.q.size(); var13++) {
               var9 = (IDInstruction)var20.q.get(var13);
               if (var9.isAssignToVar()) {
                  IDVar var14 = var9.getAssignDestination().asVar();
                  if (var14 == var10 || var11.contains(var14)) {
                     return false;
                  }
               }

               if (var9.isAssignFromVar()) {
                  IDVar var29 = var9.getAssignSource().asVar();
                  if (var29 == var10 || var11.contains(var29)) {
                     if (!(var9.getAssignDestination() instanceof IDVar var16)) {
                        return false;
                     }

                     var11.add(var16);
                  }
               }

               if (!var9.visitInstruction(var12)) {
                  return false;
               }
            }

            if (var17 == null) {
               var17 = var11;
            } else if (!var17.equals(var11)) {
               return false;
            }

            var20.xK = var10;
            var20.Dw = var11;
         }

         BasicBlock var19 = bto.q(this.cfg, (Collection)var5.stream().map(var0 -> var0.q).collect(Collectors.toList()));
         if (var19 == null) {
            return false;
         } else {
            for (ccv.eo var24 : var5) {
               if (CFGUtil.canReach(var24.q, var24.q, true, Arrays.asList(var19))) {
                  return false;
               }
            }

            IDInstruction var22 = (IDInstruction)var19.get(0);
            DUtil.modifyInstructionSize(this.ctx, var22, 2);
            long var25 = var22.getOffset();
            var22.adjustSize(-1);
            var22.setOffset(var25 + 1L);
            IDVar var26 = this.ctx.createVirtualVar(var4.getType());
            IDInstruction var27 = this.ctx.createAssign(var26, var4).withOffset(var25);
            var19.add(0, var27);

            for (ccv.eo var30 : var5) {
               ((IDInstruction)var30.q.get(var30.RF)).setAssignSource(var26);
            }

            this.invalidateChains();
            return true;
         }
      }
   }

   private class CU implements IDVisitor {
      IDVar q;
      Set RF;

      CU(IDVar var2, Set var3) {
         this.q = var2;
         this.RF = var3;
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar var4 && (var4 == this.q || this.RF.contains(var4))) {
            if (var2 instanceof IDArrayElt var5 && var5.getArray() == var4) {
               return;
            }

            if (var2 instanceof IDInstruction var6 && var6.isAssign() && (var6.getAssignSource() == var4 || var6.getAssignDestination() == var4)) {
               return;
            }

            var3.interrupt(false);
         }
      }
   }

   static class eo {
      final BasicBlock q;
      final int RF;
      IDVar xK;
      Set Dw;

      public eo(BasicBlock var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }
   }
}
