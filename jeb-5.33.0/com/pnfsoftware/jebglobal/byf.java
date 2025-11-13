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

public class byf extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = this.pC(var3, null);
         if (var4 >= 0 && this.pC(var3, var4)) {
            var1++;
         }
      }

      return var1;
   }

   private int pC(BasicBlock var1, IDNewArrayInfo var2) {
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

   private boolean pC(BasicBlock var1, int var2) {
      IDInstruction var3 = (IDInstruction)var1.get(var2);
      IDNewArrayInfo var4 = var3.getAssignSource().asNewArrayInfo();
      ArrayList var5 = new ArrayList();
      var5.add(new byf.Av(var1, var2));

      for (BasicBlock var7 : var1.getOutputBlock(0).getInputs()) {
         if (var7 != var1) {
            int var8 = this.pC(var7, var4);
            if (var8 < 0) {
               return false;
            }

            var5.add(new byf.Av(var7, var8));
         }
      }

      if (var5.size() < 2) {
         return false;
      } else {
         HashSet var17 = null;

         for (byf.Av var20 : var5) {
            IDInstruction var9 = (IDInstruction)var20.pC.get(var20.A);
            IDVar var10 = var9.getAssignDestination().asVar();
            HashSet var11 = new HashSet();
            byf.Sv var12 = new byf.Sv(var10, var11);

            for (int var13 = var20.A + 1; var13 < var20.pC.size(); var13++) {
               var9 = (IDInstruction)var20.pC.get(var13);
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

            var20.kS = var10;
            var20.wS = var11;
         }

         BasicBlock var19 = bpl.pC(this.cfg, (Collection)var5.stream().map(var0 -> var0.pC).collect(Collectors.toList()));
         if (var19 == null) {
            return false;
         } else {
            for (byf.Av var24 : var5) {
               if (CFGUtil.canReach(var24.pC, var24.pC, true, Arrays.asList(var19))) {
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

            for (byf.Av var30 : var5) {
               ((IDInstruction)var30.pC.get(var30.A)).setAssignSource(var26);
            }

            this.invalidateChains();
            return true;
         }
      }
   }

   static class Av {
      final BasicBlock pC;
      final int A;
      IDVar kS;
      Set wS;

      public Av(BasicBlock var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }
   }

   private class Sv implements IDVisitor {
      IDVar pC;
      Set A;

      Sv(IDVar var2, Set var3) {
         this.pC = var2;
         this.A = var3;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar var4 && (var4 == this.pC || this.A.contains(var4))) {
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
}
