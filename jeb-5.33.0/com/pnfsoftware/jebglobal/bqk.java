package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.List;
import java.util.Set;

class bqk implements IDVisitor {
   bqk(bqj var1, List var2, IDInstruction var3, Set var4, Set var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDArrayElt) {
         IDArrayElt var4 = var1.asArrayElt();
         if (var4.getArray() instanceof IDVar) {
            boolean var5 = false;
            if (var2 instanceof IDInstruction) {
               IDInstruction var6 = var2.asInstruction();
               if (var6.isAssign() && var6.getAssignDestination() == var1) {
                  var5 = true;
               }
            }

            int var9 = var4.getArray().asVar().getId();
            this.pC.add(new bqj.Av(this.A.getOffset(), var9, true, var5));
            if (!var5) {
               this.kS.add(var9);
            } else {
               this.wS.add(var9);
            }
         }
      } else if (var1 instanceof IDNewArrayInfo && var2 instanceof IDInstruction) {
         IDInstruction var7 = var2.asInstruction();
         if (var7.isAssignToVar() && this.A.getAssignSource() == var1) {
            int var8 = var7.getAssignDestination().asVar().getId();
            this.pC.add(new bqj.Av(this.A.getOffset(), var8, false));
         }
      }
   }
}
