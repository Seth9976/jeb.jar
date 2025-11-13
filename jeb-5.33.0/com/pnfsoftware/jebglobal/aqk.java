package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.concurrent.atomic.AtomicInteger;

class aqk implements IEVisitor {
   aqk(aqj var1, AtomicInteger var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEOperation var4) {
         OperationType var5 = var4.getOperationType();
         switch (var5) {
            case FADD:
            case FSUB:
            case FMUL:
            case FDIV:
               IWildcardTypeManager var6 = aqj.pC(this.A).getWildcardTypeManager();

               for (IEGeneric var8 : var4.getOperands()) {
                  if (var8 instanceof IEImm var9) {
                     if (!var9.isMutable()) {
                        IEImm var10 = var9.duplicateToMutable();
                        IWildcardType var11 = var10.safelyType(var6);
                        var11 = var11.updateGroup(IWildcardType.Group.FLOAT);
                        if (var10.setType(var11)) {
                           var10 = var10.normalize();
                           if (var10 != null && var2.replaceSubExpression(var9, var10)) {
                              this.pC.incrementAndGet();
                           }
                        }
                     } else {
                        IWildcardType var13 = var9.safelyType(var6);
                        if (!var13.isFloat()) {
                           if (var13.isUpdatable()) {
                              var13 = var13.updateGroup(IWildcardType.Group.FLOAT);
                           } else {
                              var8.setType(null);
                              var13 = ((IEImm)var8).safelyType(var6);
                              var13 = var13.updateGroup(IWildcardType.Group.FLOAT);
                           }

                           if (var9.setType(var13)) {
                              IEImm var17 = var9.normalize();
                              if (var17 != null && var2.replaceSubExpression(var9, var17)) {
                                 this.pC.incrementAndGet();
                              }
                           }
                        }
                     }
                  }
               }
         }
      }
   }
}
