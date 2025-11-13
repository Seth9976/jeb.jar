package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.ArrayList;
import java.util.Collection;

public class ash {
   IERoutineContext pC;
   boolean A;
   ake kS;

   public ash(IERoutineContext var1) {
      this(var1, true);
   }

   public ash(IERoutineContext var1, boolean var2) {
      this.pC = var1;
      this.A = var2;
      this.kS = new ake(var1);
   }

   public IEGeneric pC(IEGeneric var1) {
      ash.Av var2 = new ash.Av();
      var1.visitDepthPost(var2);
      if (var2.pC == 0) {
         return null;
      } else {
         return var2.A != null ? var2.A : var1;
      }
   }

   public IEGeneric A(IEGeneric var1) {
      if (var1 instanceof IEOperation) {
         return this.pC((IEOperation)var1);
      } else {
         return var1 instanceof IECond ? this.pC((IECond)var1) : null;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IEGeneric pC(IEOperation var1) {
      OperationType var3 = var1.getOperationType();
      switch (var3) {
         case ADD:
         case MUL:
            IEGeneric var14 = var1.getOperand1();
            IEGeneric var16 = var1.getOperand2();
            if (this.A) {
               ArrayList var6 = new ArrayList();
               var6.addAll(this.pC(var3, var14));
               var6.addAll(this.pC(var3, var16));
               if (var6.size() > 2) {
                  ArrayList var7 = new ArrayList(var6);
                  var7.sort(this.kS);
                  ArrayList var8 = var7;
                  ArrayList var9 = new ArrayList();
                  IEGeneric var10 = null;

                  while (var10 == null) {
                     while (var8.size() >= 2) {
                        var9.add(this.pC.createOperation(var3, (IEGeneric)var8.remove(0), (IEGeneric)var8.remove(0)));
                     }

                     if (var8.size() == 1) {
                        var9.add((IEGeneric)var8.remove(0));
                     } else if (var8.size() != 0) {
                        throw new IllegalStateException("Associative internal error");
                     }

                     if (var9.size() == 1) {
                        var10 = (IEGeneric)var9.get(0);
                     } else {
                        ArrayList var11 = var8;
                        var8 = var9;
                        var9 = var11;
                     }
                  }

                  if (var10.equalsEx(var1, false)) {
                     return null;
                  }

                  return var10;
               }
            }

            if (this.kS.pC(var14, var16) > 0) {
               return this.pC.createOperation(var3, var16, var14);
            }
            break;
         case AND:
         case OR:
         case XOR:
         case LOG_EQ:
         case LOG_NEQ:
         case CARRY:
            IEGeneric var13 = var1.getOperand1();
            IEGeneric var15 = var1.getOperand2();
            if (this.kS.pC(var13, var15) > 0) {
               return this.pC.createOperation(var3, var15, var13);
            }
            break;
         case GT_S:
         case GT_U:
         case GE_S:
         case GE_U:
         case LT_S:
         case LT_U:
         case LE_S:
         case LE_U:
         case FEQ:
         case FNE:
         case FLT:
         case FGT:
         case FLE:
         case FGE:
            IEGeneric var4 = var1.getOperand1();
            IEGeneric var5 = var1.getOperand2();
            if (this.kS.pC(var4, var5) > 0) {
               var3 = EUtil.getMirrorOperation(var3);
               if (var3 != null) {
                  return this.pC.createOperation(var3, var5, var4);
               }
            }
         case SUB:
         case NOT:
         case ROL:
         case ROR:
         case SAR:
         case SHL:
         case SHR:
         case PAR:
         case LOG_AND:
         case LOG_NOT:
         case LOG_OR:
         case DIV_S:
         case DIV_U:
         case REM_S:
         case REM_U:
         case DIV2_S:
         case DIV2_U:
         case MUL2_S:
         case MUL2_U:
         case CAST:
         case CAST_S:
      }

      return null;
   }

   private Collection pC(OperationType var1, IEGeneric var2) {
      ArrayList var3 = new ArrayList();
      if (EUtil.isOperation(var2, var1)) {
         var3.addAll(this.pC(var1, ((IEOperation)var2).getOperand1()));
         var3.addAll(this.pC(var1, ((IEOperation)var2).getOperand2()));
      } else {
         var3.add(var2);
      }

      return var3;
   }

   private IEGeneric pC(IECond var1) {
      IEGeneric var2 = var1.getCondition();
      if (var2.isOperation() && var2.asOperation().getOperationType().isLogical()) {
         IEOperation var3 = var2.asOperation();
         OperationType var4 = EUtil.getReverseOperation(var3.getOperationType());
         if (var4 != null || var3.getOperationType() == OperationType.LOG_NOT) {
            IEGeneric var5 = var1.getExpressionTrue();
            IEGeneric var6 = var1.getExpressionFalse();
            if (this.kS.pC(var5, var6) > 0) {
               Object var7;
               if (var4 == null) {
                  var7 = var3.getOperand1();
               } else {
                  var7 = this.pC.createOperation(var4, var3.getOperand1(), var3.getOperand2());
               }

               return this.pC.createCond((IEGeneric)var7, var6, var5);
            }
         }
      }

      return null;
   }

   class Av implements IEVisitor {
      int pC;
      IEGeneric A;

      public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         IEGeneric var4 = ash.this.A(var1);
         if (var4 != null) {
            var4.setType(var1.getType());
            if (var2 == null) {
               this.A = var4;
               this.pC++;
            } else if (var2.replaceSubExpression(var1, var4)) {
               this.pC++;
            }
         }
      }
   }
}
