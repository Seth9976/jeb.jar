package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.SubstitutionDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class arm extends AbstractEExpressionOptimizer {
   public static final SubstitutionDefinition[] pC = new SubstitutionDefinition[]{
      Util.SD(Util.N(O.ADD, Util.N(O.ADD, Util.N(O.NOT, Util.L(1)), Util.L(0)), Util.LC(1L)), Util.N(O.SUB, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.ADD, Util.N(O.ADD, Util.N(O.NOT, Util.L(1)), Util.LC(1L)), Util.L(0)), Util.N(O.SUB, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.ADD, Util.N(O.ADD, Util.L(0), Util.LC(1L)), Util.N(O.NOT, Util.L(1))), Util.N(O.SUB, Util.L(0), Util.L(1)))
   };
   public static final SubstitutionDefinition[] A = new SubstitutionDefinition[]{
      Util.SD(
         Util.N(
            O.SUB,
            Util.L(0),
            Util.N(O.MUL, Util.N(O.COND, Util.N(O.EQ, Util.L(1), Util.LC(0L)), Util.LC(0L), Util.N(O.DIV_S, Util.L(0), Util.L(1))), Util.L(1))
         ),
         Util.N(O.REM_S, Util.L(0), Util.L(1))
      ),
      Util.SD(
         Util.N(
            O.SUB,
            Util.L(0),
            Util.N(O.MUL, Util.N(O.COND, Util.N(O.EQ, Util.L(1), Util.LC(0L)), Util.LC(0L), Util.N(O.DIV_U, Util.L(0), Util.L(1))), Util.L(1))
         ),
         Util.N(O.REM_U, Util.L(0), Util.L(1))
      )
   };

   public arm() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation var2)) {
         return null;
      } else {
         OperationType var3 = var2.getOperationType();
         if (var3 == OperationType.ADD || var3 == OperationType.SUB) {
            IEGeneric var4 = var2.getOperand1();
            IEGeneric var5 = var2.getOperand2();
            IEGeneric var6 = null;
            IEGeneric var7 = null;
            IEImm var8 = null;
            IEImm var9 = null;
            if (EUtil.isOperation(var4, OperationType.MUL) && ((IEOperation)var4).getOperand2() instanceof IEImm) {
               var6 = ((IEOperation)var4).getOperand1();
               var8 = (IEImm)((IEOperation)var4).getOperand2();
            }

            if (EUtil.isOperation(var5, OperationType.MUL) && ((IEOperation)var5).getOperand2() instanceof IEImm) {
               var7 = ((IEOperation)var5).getOperand1();
               var9 = (IEImm)((IEOperation)var5).getOperand2();
            }

            IEImm var10 = null;
            IEGeneric var11 = null;
            OperationType var12 = null;
            if (akt.pC(var4, var5)) {
               var11 = var4;
               if (var3 == OperationType.SUB) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var4.getBitsize()), true);
               }

               var10 = this.ectx.createImm(2L, var4.getBitsize());
               var12 = OperationType.MUL;
            } else if (var6 != null && akt.pC(var6, var5)) {
               var11 = var5;
               IEImm var21 = this.ectx.createImm(1L, var8.getBitsize());
               var10 = var3 == OperationType.ADD ? this.pC(var8, var21) : this.A(var8, var21);
               var12 = ((IEOperation)var4).getOperationType();
            } else if (var7 != null && akt.pC(var4, var7)) {
               var11 = var4;
               IEImm var13 = this.ectx.createImm(1L, var9.getBitsize());
               var10 = var3 == OperationType.ADD ? this.pC(var13, var9) : this.A(var13, var9);
               var12 = ((IEOperation)var5).getOperationType();
            } else if (var6 != null && var7 != null && ((IEOperation)var4).getOperationType() == ((IEOperation)var5).getOperationType() && akt.pC(var6, var7)) {
               var11 = var6;
               var10 = var3 == OperationType.ADD ? this.pC(var8, var9) : this.A(var8, var9);
               var12 = ((IEOperation)var4).getOperationType();
            }

            if (var10 != null) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var12, var11, var10));
            }
         }

         IEGeneric var15 = this.doSubstitution(var1, pC);
         if (var15 != null) {
            return AbstractEExpressionOptimizer.EOR.create(var15);
         } else {
            if (var3 == OperationType.MUL || var3 == OperationType.ADD || var3 == OperationType.AND || var3 == OperationType.OR || var3 == OperationType.XOR) {
               IEGeneric var16 = var2.getOperand1();
               IEGeneric var18 = var2.getOperand2();
               if (var18 instanceof IEImm && EUtil.isOperation(var16, var3) && ((IEOperation)var16).getOperand2() instanceof IEImm) {
                  IEImm var19 = (IEImm)((IEOperation)var16).getOperand2();

                  IEImm var20;
                  try {
                     var20 = this.ectx.createOperation(var3, var19, var18).evaluate(null);
                  } catch (Exception var14) {
                     return null;
                  }

                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var3, ((IEOperation)var16).getOperand1(), var20));
               }
            }

            IEGeneric var17 = this.doSubstitution(var1, A);
            return var17 != null ? AbstractEExpressionOptimizer.EOR.create(var17) : null;
         }
      }
   }

   private IEImm pC(IEImm var1, IEImm var2) {
      boolean var3 = false;
      if (var1._testbit(var1.getBitsize() - 1) == var2._testbit(var2.getBitsize() - 1)) {
         var3 = true;
      }

      IEImm var4 = var1._add(var2);
      return var3 && var1._testbit(var1.getBitsize() - 1) != var4._testbit(var4.getBitsize() - 1) ? null : var4;
   }

   private IEImm A(IEImm var1, IEImm var2) {
      boolean var3 = false;
      if (var1._testbit(var1.getBitsize() - 1) != var2._testbit(var2.getBitsize() - 1)) {
         var3 = true;
      }

      IEImm var4 = var1._sub(var2);
      return var3 && var1._testbit(var1.getBitsize() - 1) != var4._testbit(var4.getBitsize() - 1) ? null : var4;
   }
}
