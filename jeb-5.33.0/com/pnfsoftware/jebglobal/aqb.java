package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.concurrent.atomic.AtomicInteger;

class aqb implements IEVisitor {
   aqb(aqa var1, IWildcardTypeManager var2, AtomicInteger var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem()) {
         IEGeneric var4 = var1.asMem().getReference();
         Couple var5 = aqa.pC(var4);
         if (var5 != null) {
            IEVar var6 = (IEVar)var5.getFirst();
            long var7 = (Long)var5.getSecond();
            if (var6.getType() != null && var6.getType().getNativeType() != null) {
               INativeType var9 = TypeUtil.getNonAlias(var6.getType().getNativeType());
               if (var9 instanceof IReferenceType) {
                  INativeType var10 = TypeUtil.getNonAlias(((IReferenceType)var9).getPointedType());
                  if (var10 instanceof IStructureType) {
                     return;
                  }
               }
            }

            long var16 = var6.getAddress();
            IEVar var11 = aqa.pC(this.kS).getStackManager().getVariable(var16);
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
                        var17 = this.pC.create(var19);
                        aqa.A(this.kS).acquireNativeItem(var17);
                     }
                  }
               }

               if (var17 == null) {
                  var17 = this.pC.createWithMaximumBitsize(var13);
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
                  this.A.incrementAndGet();
               }
            }
         }
      }
   }
}
