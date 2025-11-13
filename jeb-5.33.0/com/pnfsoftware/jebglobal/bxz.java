package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.ArrayList;
import java.util.Collection;

class bxz implements IDVisitor {
   bxz(bxy var1, IDInstruction var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInstanceField var4 && var4.isArrayLength()) {
         IDExpression var5 = var4.getInstance();
         ArrayList var6 = null;
         if (var5 instanceof IDVar var7) {
            Collection var10 = this.A.dfa.getUseDefs(this.pC.getOffset(), var7.getId(), 3);
            if (var10.isEmpty() || var10.size() >= 3) {
               return;
            }

            for (Long var12 : var10) {
               IDInstruction var13 = (IDInstruction)this.A.cfg.getInstruction(var12);
               if (var13 == null || !var13.isAssignToVar(var7.getId())) {
                  return;
               }

               IDExpression var14 = var13.getAssignSource();
               if (var14.isCastOperation()) {
                  var14 = var14.asOperation().getOperand2();
               }

               if (!(var14 instanceof IDNewArrayInfo var15)) {
                  return;
               }

               if (var6 == null) {
                  var6 = new ArrayList();
               }

               var6.add(var15);
            }
         } else if (var5 instanceof IDNewArrayInfo var8) {
            if (var6 == null) {
               var6 = new ArrayList();
            }

            var6.add(var8);
         }

         if (var6 != null && !var6.isEmpty()) {
            IDImm var16 = null;

            for (IDNewArrayInfo var9 : var6) {
               if (!(var9.getSize() instanceof IDImm var19)) {
                  return;
               }

               if (var16 == null) {
                  var16 = var19;
               } else if (!var16.equals(var19)) {
                  return;
               }
            }

            if (var16 != null) {
               IDImm var18 = var16.duplicate();
               if (var2.replaceSubExpression(var1, var18)) {
                  var3.setReplacedNode(var18);
                  this.A.pC++;
               }
            }
         }
      }
   }
}
