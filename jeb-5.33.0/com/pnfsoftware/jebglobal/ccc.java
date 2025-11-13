package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;

class ccc implements IDVisitor {
   ccc(ccb.K var1, IDVar var2, ArrayDeque var3, Set var4, List var5, IDInstruction var6, List var7) {
      this.sY = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
      this.UT = var6;
      this.E = var7;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.pC) {
         if (var2 instanceof IDInstruction var4) {
            if (var4.isAssignFromVar(this.pC.getId()) && var4.isAssignToVar()) {
               IDVar var5 = var4.getAssignDestination().asVar();
               this.A.add(new Couple(var4, var5));
               this.kS.add(var4.getOffset());
               return;
            }

            var3.interrupt(false);
         } else {
            if (var2 instanceof IDInstanceField) {
               return;
            }

            if (var2 instanceof IDArrayElt) {
               IDArrayElt var9 = var2.asArrayElt();
               if (var9.getArray() == this.pC) {
                  return;
               }

               var3.interrupt(false);
            } else {
               if (var2 instanceof IDCallInfo
                  && ((IDCallInfo)var2).getMethodSignature().equals("Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V")) {
                  List var10 = ((IDCallInfo)var2).getArguments();
                  if (var10.get(2) == this.pC
                     && ((IDExpression)var10.get(1)).isConstantImm()
                     && ((IDExpression)var10.get(3)).isConstantImm()
                     && ((IDExpression)var10.get(4)).isConstantImm()) {
                     Object var12 = this.sY.pC((IDExpression)var10.get(0));
                     if (var12 != null) {
                        int var6 = (int)((IDExpression)var10.get(1)).asImm().getValueAsLong();
                        int var7 = (int)((IDExpression)var10.get(3)).asImm().getValueAsLong();
                        int var8 = (int)((IDExpression)var10.get(4)).asImm().getValueAsLong();
                        if (this.sY.pC(var12, var6, var8) && var7 >= 0 && var8 >= 0 && var7 + var8 >= var7) {
                           this.wS.add(new ccb.Sv(this.UT, var12, var6, this.pC, var7, var8));
                           return;
                        }
                     }
                  }
               }

               var3.interrupt(false);
            }
         }
      }

      if (var1 instanceof IDArrayElt) {
         IDArrayElt var11 = var1.asArrayElt();
         if (var11.getArray() == this.pC) {
            this.E.add(new ccb.Av(this.UT, var11, var2));
         }
      }
   }
}
