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

class buq implements IDVisitor {
   buq(bup var1, List var2, IDInstruction var3, Set var4, Set var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
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
            this.q.add(new bup.eo(this.RF.getOffset(), var9, true, var5));
            if (!var5) {
               this.xK.add(var9);
            } else {
               this.Dw.add(var9);
            }
         }
      } else if (var1 instanceof IDNewArrayInfo && var2 instanceof IDInstruction) {
         IDInstruction var7 = var2.asInstruction();
         if (var7.isAssignToVar() && this.RF.getAssignSource() == var1) {
            int var8 = var7.getAssignDestination().asVar().getId();
            this.q.add(new bup.eo(this.RF.getOffset(), var8, false));
         }
      }
   }
}
