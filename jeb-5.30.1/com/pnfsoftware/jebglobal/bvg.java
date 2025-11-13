package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayDeque;

class bvg implements IDVisitor {
   bvg(IDVar var1, ArrayDeque var2, boolean var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.q) {
         if (var2 instanceof IDInstruction var4) {
            if (var4.isAssignFromVar(this.q.getId()) && var4.isAssignToVar()) {
               IDVar var11 = var4.getAssignDestination().asVar();
               this.RF.add(new Couple(var4, var11));
               return;
            }

            var3.interrupt(false);
         } else {
            if (var2 instanceof IDInstanceField) {
               return;
            }

            if (var2 instanceof IDCallInfo var5) {
               if (this.xK) {
                  var3.interrupt(false);
               } else {
                  DInvokeType var7 = var5.getInvokeType();
                  if (var7 == DInvokeType.VIRTUAL || var7 == DInvokeType.INTERFACE || var7 == DInvokeType.DIRECT) {
                     int var8 = 0;

                     for (IDExpression var10 : var5.getArguments()) {
                        if (var10 == this.q && var8 != 0) {
                           var3.interrupt(false);
                           break;
                        }

                        var8++;
                     }

                     return;
                  }

                  var3.interrupt(false);
               }
            } else if (var2 instanceof IDArrayElt var6) {
               if (var6.getArray() == this.q) {
                  return;
               }

               var3.interrupt(false);
            } else {
               var3.interrupt(false);
            }
         }
      }
   }
}
