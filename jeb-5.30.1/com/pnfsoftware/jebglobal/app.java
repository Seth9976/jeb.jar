package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.concurrent.atomic.AtomicInteger;

class app implements IEVisitor {
   app(apm var1, AtomicInteger var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem()) {
         IEGeneric var4 = var1.asMem().getReference();
         Couple var5 = apm.q(var4);
         if (var5 != null) {
            IEVar var6 = (IEVar)var5.getFirst();
            long var7 = (Long)var5.getSecond();
            if (var6.getType() != null && var6.getType().getNativeType() != null && TypeUtil.isPointer(var6.getType().getNativeType())) {
               bbt var9 = (bbt)var6.getType().getNativeType();
               if (TypeUtil.getNonAlias(var9.getPointedType()) instanceof bbv) {
                  return;
               }
            }

            long var16 = var6.getAddress();
            IEVar var11 = this.RF.q.getStackManager().getVariable(var16);
            if (var11 == null) {
               return;
            }

            Object var12 = null;
            int var13 = var1.asMem().getBitsize();
            if (var11.getBitsize() == var13 && var7 == 0L) {
               IWildcardType var17 = null;
               if (var6.getType().isDefined()) {
                  INativeType var19 = var6.getType().getNativeType();
                  if (TypeUtil.isReference(var19)) {
                     var19 = ((IReferenceType)var19).getPointedType();
                     if (var19.getSize() * 8 == var13) {
                        var17 = this.RF.Dw.create(var19);
                        this.RF.q.acquireNativeItem(var17);
                     }
                  }
               }

               if (var17 == null) {
                  var17 = this.RF.Dw.createWithMaximumBitsize(var13);
               }

               var11.setType(var17, null);
               var12 = var11;
            } else {
               int var14 = (int)var7 * 8;
               int var15 = var14 + var13;
               if (var15 <= var11.getBitsize()) {
                  var12 = var11.slice(var14, var15);
               }
            }

            if (var12 != null) {
               boolean var18 = var2.replaceSubExpression(var1, (IEGeneric)var12);
               if (var18) {
                  this.q.incrementAndGet();
               }
            }
         }
      }
   }
}
