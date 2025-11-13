package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class arw extends AbstractEExpressionOptimizer {
   public arw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IESlice) {
         IEGeneric var2 = var1.asSlice().getWholeExpression();
         if (var2 instanceof IECompose) {
            List var3 = var2.asCompose().getParts();
            IERange var4 = var1.asSlice().getRange();
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            int var8 = var3.size();
            int var9 = 0;
            int var10 = 0;
            Iterator var11 = var3.iterator();

            while (true) {
               if (var11.hasNext()) {
                  IEGeneric var12 = (IEGeneric)var11.next();
                  int var13 = var5 + var12.getBitsize();
                  if (var4.getBegin() >= var13) {
                     if (!var10 && EUtil.countVariableUse(var12) > 0) {
                        var10 = 1;
                     }

                     var7++;
                     var9 = var13;
                  }

                  if (var4.getEnd() > var5) {
                     var5 = var13;
                     var6++;
                     continue;
                  }

                  var8 = var6;
               }

               if (var7 > 0 || var8 < var3.size()) {
                  if (!var10) {
                     for (IEGeneric var43 : var3.subList(var8, var3.size())) {
                        if (EUtil.countVariableUse(var43) > 0) {
                           var10 = 1;
                           break;
                        }
                     }
                  }

                  IERange var42 = var4.shift(-var9);
                  IEGeneric var44 = EUtil.compose(this.ectx, var3.subList(var7, var8)).slice(var42);
                  return AbstractEExpressionOptimizer.EOR.create(var44, (boolean)var10);
               }

               if (var4.getBegin() == 0) {
                  var5 = var1.getBitsize();
                  Assert.a(var4.getEnd() == var5);
                  var7 = 0;
                  IEGeneric var38 = null;

                  for (var8 = 0; var8 < var3.size(); var8++) {
                     var38 = (IEGeneric)var3.get(var8);
                     var6 = var7 + var38.getBitsize();
                     if (var6 >= var5) {
                        break;
                     }

                     var7 = var6;
                  }

                  if (var8 == var3.size() - 1 && var38 instanceof IEImm) {
                     var10 = var5 - var7;
                     ArrayList var40 = new ArrayList(var3);
                     var40.set(var8, var38.part(var10));
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.compose(this.ectx, var40));
                  }
               }
               break;
            }
         }
      }

      if (var1 instanceof IECompose) {
         Object var14 = var1.asCompose().getParts();
         boolean var17 = false;

         arw.Av var20;
         do {
            if (var14.size() <= 1) {
               var20 = null;
            } else {
               var20 = this.pC((List)var14);
               if (var20 != null) {
                  var17 = true;
                  ArrayList var25 = new ArrayList();
                  int var29 = 0;

                  for (IEGeneric var36 : var14) {
                     if (var29 == var20.pC) {
                        var25.add(var20.A);
                     } else if (var29 != var20.pC + 1) {
                        var25.add(var36);
                     }

                     var29++;
                  }

                  var14 = var25;
               }
            }
         } while (var20 != null);

         if (var17) {
            if (var14.size() == 1) {
               return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var14.get(0));
            }

            return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCompose((Collection)var14));
         }
      }

      if (var1.isOperation(OperationType.OR)) {
         ArrayList var15 = new ArrayList();
         ArrayList var18 = new ArrayList();
         var18.add(var1);

         while (!var18.isEmpty()) {
            IEGeneric var21 = (IEGeneric)var18.remove(0);
            if (var21.isOperation(OperationType.OR)) {
               var18.add(var21.asOperation().getOperand1());
               var18.add(var21.asOperation().getOperand2());
            } else {
               var15.add(var21);
            }
         }

         int var22 = 0;
         ArrayList var26 = new ArrayList();

         while (var26 != null && !var15.isEmpty()) {
            int var30 = var15.size();

            for (int var34 = 0; var34 < var15.size(); var34++) {
               IEGeneric var37 = (IEGeneric)var15.get(var34);
               if (var22 != 0) {
                  if (!var37.isOperation(OperationType.MUL)) {
                     var26 = null;
                     break;
                  }

                  if (EUtil.isImmValue(var37.asOperation().getOperand2(), 1L << var22)) {
                     var37 = var37.asOperation().getOperand1();
                  }
               }

               if (var37 instanceof IECompose) {
                  if (EUtil.isZeroExtend(var37)) {
                     var26.add(var37.asCompose().getLowPart());
                     var22 += var37.asCompose().getLowPart().getBitsize();
                     var15.remove(var34);
                  } else {
                     var26 = null;
                  }
                  break;
               }
            }

            if (var15.size() == var30) {
               var26 = null;
               break;
            }
         }

         if (var26 != null) {
            return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCompose(var26).zeroExtend(var1.getBitsize()));
         }
      }

      if (var1.isOperation(OperationType.AND)) {
         IEGeneric var16 = var1.asOperation().getOperand1();
         IEGeneric var19 = var1.asOperation().getOperand2();
         if (var19.isImm() && EUtil.isZeroExtend(var16)) {
            IEGeneric var23 = var16.asCompose().getLowPart();
            IEImm var27 = EUtil.one(var1.getBitsize());
            IEImm var31 = var27._shl(var23.getBitsize())._sub(var27);
            if (var31.equalsEx(var19, false)) {
               return AbstractEExpressionOptimizer.EOR.create(var16.asCompose());
            }
         }
      }

      return null;
   }

   private arw.Av pC(List var1) {
      IEGeneric var2 = null;
      int var3 = 0;

      for (IEGeneric var5 : var1) {
         if (var5 instanceof IESlice) {
            if (var2 instanceof IESlice && this.pC(var2.asSlice(), (IESlice)var5)) {
               IEGeneric var6 = var2.asSlice().getWholeExpression().slice(var2.asSlice().getBitStart(), ((IESlice)var5).getBitEnd());
               return new arw.Av(var3 - 1, var6);
            }
         } else if (var5 instanceof IEMem) {
            if (var2 instanceof IEMem) {
               boolean var10 = this.ectx.getGlobalContext().isBigEndian();
               IEMem var7 = (var10 ? var5 : var2).asMem();
               IEMem var8 = (var10 ? var2 : var5).asMem();
               IEGeneric var9 = this.pC(var7, var8);
               if (var9 != null) {
                  return new arw.Av(var3 - 1, var9);
               }
            }
         } else if (var5 instanceof IEImm && var2 instanceof IEImm) {
            IEImm var11 = var5.asImm().expand(var5.getBitsize() + var2.getBitsize())._shl(var2.getBitsize());
            IEImm var12 = var11._or(var2.asImm().expand(var11.getBitsize()));
            return new arw.Av(var3 - 1, var12);
         }

         var2 = var5;
         var3++;
      }

      return null;
   }

   private boolean pC(IESlice var1, IESlice var2) {
      return var1.getWholeExpression().equals(var2.getWholeExpression()) && var1.getBitEnd() == var2.getBitStart();
   }

   private IEGeneric pC(IEMem var1, IEMem var2) {
      IEGeneric var3 = var1.getReference();
      IEGeneric var4 = var2.getReference();
      long var6 = 0L;
      IEGeneric var5;
      if (var3.isOperation(OperationType.ADD)) {
         IEGeneric var8 = var3.asOperation().getOperand1();
         IEGeneric var9 = var3.asOperation().getOperand2();
         if (var9.isImm()) {
            var5 = var8;
            var6 = var9.asImm().getValueAsLong();
         } else {
            if (!var8.isImm()) {
               return null;
            }

            var5 = var9;
            var6 = var8.asImm().getValueAsLong();
         }
      } else {
         var5 = var3;
      }

      if (!this.pC(var1.getBitsize())) {
         return null;
      } else {
         long var13 = var6 + var1.getBitsize() / 8;
         IEMem var10 = this.ectx.createMem(var3, var1.getBitsize() + var2.getBitsize());
         if (!var4.isOperation(OperationType.ADD)) {
            return var13 == 0L ? var10 : null;
         } else {
            IEGeneric var11 = var4.asOperation().getOperand1();
            IEGeneric var12 = var4.asOperation().getOperand2();
            if (var12.isImm()) {
               return akt.pC(var11, var5) && var12.asImm().getValueAsLong() == var13 ? var10 : null;
            } else if (!var11.isImm()) {
               return null;
            } else {
               return akt.pC(var12, var5) && var11.asImm().getValueAsLong() == var13 ? var10 : null;
            }
         }
      }
   }

   private boolean pC(int var1) {
      return var1 >>> 3 << 3 == var1;
   }

   private static class Av {
      int pC;
      IEGeneric A;

      public Av(int var1, IEGeneric var2) {
         this.pC = var1;
         this.A = var2;
      }
   }
}
