package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import java.util.Iterator;
import java.util.Set;

public class avb extends atk {
   @Override
   public boolean RF(BasicBlock var1, int var2, int var3, IEVar var4, IEGeneric var5, int var6, Set var7) {
      boolean var8 = super.RF(var1, var2, var3, var4, var5, var6, var7);
      if (var8) {
         return true;
      } else {
         if (xK(var5)) {
            boolean var9 = true;
            Iterator var10 = var7.iterator();

            label63: {
               while (true) {
                  if (!var10.hasNext()) {
                     break label63;
                  }

                  int var11 = (Integer)var10.next();
                  IEStatement var12 = (IEStatement)var1.get(var11);
                  if (!(var12 instanceof IEAssign)) {
                     break;
                  }

                  IEGeneric var13 = ((IEAssign)var12).getLeftOperand();
                  if (!(var13 instanceof IEMem)) {
                     break;
                  }

                  IEGeneric var14 = ((IEMem)var13).getReference();
                  if (!amw.q(var14, var4)) {
                     if (!EUtil.isOperation(var14, OperationType.ADD, OperationType.SUB)) {
                        break;
                     }

                     IEOperation var15 = (IEOperation)var14;
                     if (!amw.q(var15.getOperand1(), var4) || !(var15.getOperand2() instanceof IEImm)) {
                        break;
                     }
                  }
               }

               var9 = false;
            }

            if (var9) {
               return true;
            }
         }

         if (var6 > 1 && EUtil.isOperation(var5, OperationType.MUL)) {
            IEOperation var16 = (IEOperation)var5;
            if (EUtil.isZeroExtend(var16.getOperand1()) && EUtil.isZeroExtend(var16.getOperand2())) {
               for (int var18 : var7) {
                  IEStatement var19 = (IEStatement)var1.get(var18);
                  if (!(var19 instanceof IEAssign)) {
                     return false;
                  }

                  if (!((IEAssign)var19).getRightOperand().visitDepthPre((var0, var1x, var2x) -> {
                     if (var0 instanceof IESlice) {
                        var2x.skipChildren();
                     } else if (!RF(var0)) {
                        var2x.interrupt(false);
                     }
                  })) {
                     return false;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   @Override
   protected int q(IEVar var1, IEGeneric var2, IEStatement var3, int var4, int var5) {
      var4 = super.q(var1, var2, var3, var4, var5);
      if (var5 > 1 || var5 + var4 <= 1) {
         return var4;
      } else if (q(var2)) {
         return Math.min(var5 + var4, 1);
      } else if (var4 > 1 && var2 instanceof IECompose) {
         for (IEGeneric var7 : var2.asCompose()) {
            if (!q(var7)) {
               return var4;
            }
         }

         return Math.min(var5 + var4, 1);
      } else {
         return var4;
      }
   }

   private static boolean q(IEGeneric var0) {
      return var0 instanceof IEImm || var0 instanceof IEVar || var0 instanceof IESlice && ((IESlice)var0).getWholeExpression() instanceof IEVar;
   }

   private static boolean RF(IEGeneric var0) {
      return var0 instanceof IEOperation || var0 instanceof IEVar || var0 instanceof IEImm || var0 instanceof IECompose;
   }

   private static boolean xK(IEGeneric var0) {
      if (var0 instanceof IEVar) {
         return q((IEVar)var0);
      } else if (!EUtil.isOperation(var0, OperationType.ADD)) {
         return false;
      } else {
         IEOperation var1 = (IEOperation)var0;
         return var1.getOperand1() instanceof IEVar && xK(var1.getOperand1()) && var1.getOperand2() instanceof IEImm;
      }
   }

   private static boolean q(IEVar var0) {
      if (var0.getType() != null) {
         INativeType var1 = var0.getType().getNativeType();
         if (var1 instanceof IReferenceType) {
            return true;
         }
      }

      return false;
   }
}
