package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Comparator;

public class ake implements Comparator {
   private IERoutineContext pC;

   public ake(IERoutineContext var1) {
      this.pC = var1;
   }

   public int pC(IEGeneric var1, IEGeneric var2) {
      int var3 = var1.getPriority();
      int var4 = var2.getPriority();
      if (var3 != var4 || var3 == 0) {
         return var3 - var4;
      } else if (var1 instanceof IEImm) {
         Assert.a(var2 instanceof IEImm);
         return ((IEImm)var1).getValue().compareTo(((IEImm)var2).getValue());
      } else if (var1 instanceof IEVar) {
         Assert.a(var2 instanceof IEVar);
         int var18 = ((IEVar)var1).getId();
         int var25 = ((IEVar)var2).getId();
         if (var18 == var25) {
            return 0;
         } else if (var18 >= 0 && var25 >= 0) {
            return var18 - var25;
         } else {
            Integer var32 = this.pC.getUnderlyingRegisterId(var18);
            Integer var35 = this.pC.getUnderlyingRegisterId(var25);
            if (var32 != null && var35 != null) {
               int var38 = var32 - var35;
               if (var38 != 0) {
                  return var38;
               }

               String var39 = ((aku)var1).getName();
               String var11 = ((aku)var2).getName();
               if (var39 != null && var11 != null) {
                  return var39.compareTo(var11);
               }
            }

            if (var18 >= 0) {
               return -1;
            } else {
               return var25 >= 0 ? 1 : var25 - var18;
            }
         }
      } else if (var1 instanceof IEMem) {
         Assert.a(var2 instanceof IEMem);
         IEGeneric var17 = ((IEMem)var1).getReference();
         IEGeneric var24 = ((IEMem)var2).getReference();
         return this.pC(var17, var24);
      } else if (var1 instanceof IECompose) {
         Assert.a(var2 instanceof IECompose);
         int var16 = ((IECompose)var1).getPartsCount();
         int var23 = ((IECompose)var2).getPartsCount();
         if (var16 != var23) {
            return var16 - var23;
         } else {
            for (int var31 = 0; var31 < var16; var31++) {
               IEGeneric var34 = ((IECompose)var1).getPart(var31);
               IEGeneric var37 = ((IECompose)var2).getPart(var31);
               int var10 = this.pC(var34, var37);
               if (var10 != 0) {
                  return var10;
               }
            }

            return 0;
         }
      } else if (var1 instanceof IESlice) {
         Assert.a(var2 instanceof IESlice);
         IEGeneric var15 = ((IESlice)var1).getWholeExpression();
         IEGeneric var22 = ((IESlice)var2).getWholeExpression();
         return this.pC(var15, var22);
      } else if (var1 instanceof IECond) {
         Assert.a(var2 instanceof IECond);
         IEGeneric var12 = ((IECond)var1).getCondition();
         IEGeneric var19 = ((IECond)var2).getCondition();
         int var28 = this.pC(var12, var19);
         if (var28 != 0) {
            return var28;
         } else {
            var12 = ((IECond)var1).getExpressionTrue();
            var19 = ((IECond)var2).getExpressionTrue();
            var28 = this.pC(var12, var19);
            if (var28 != 0) {
               return var28;
            } else {
               var12 = ((IECond)var1).getExpressionFalse();
               var19 = ((IECond)var2).getExpressionFalse();
               var28 = this.pC(var12, var19);
               return var28 != 0 ? var28 : 0;
            }
         }
      } else if (var1 instanceof IEOperation) {
         Assert.a(var2 instanceof IEOperation);
         OperationType var5 = ((IEOperation)var1).getOperationType();
         OperationType var6 = ((IEOperation)var2).getOperationType();
         int var7 = var5.ordinal() - var6.ordinal();
         if (var7 != 0) {
            return var7;
         } else {
            IEGeneric var8 = ((IEOperation)var1).getOperand1();
            IEGeneric var9 = ((IEOperation)var2).getOperand1();
            var7 = this.pC(var8, var9);
            if (var7 != 0) {
               return var7;
            } else {
               var8 = ((IEOperation)var1).getOperand2();
               var9 = ((IEOperation)var2).getOperand2();
               if (var8 == null) {
                  return var9 == null ? 0 : -1;
               } else if (var9 == null) {
                  return 1;
               } else {
                  var7 = this.pC(var8, var9);
                  return var7 != 0 ? var7 : 0;
               }
            }
         }
      } else {
         return 0;
      }
   }
}
