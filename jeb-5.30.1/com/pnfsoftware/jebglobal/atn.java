package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

public class atn extends AbstractEExpressionOptimizer {
   public atn() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   public AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (EUtil.isOperation(var1, OperationType.ADD, OperationType.SUB)) {
         IEOperation var2 = var1.asOperation();
         IEGeneric var3 = var2.getOperand1();
         IEGeneric var4 = var2.getOperand2();
         if ((var3 instanceof IEOperation || var4 instanceof IEOperation) && new atn.eo().q(var1)) {
            return AbstractEExpressionOptimizer.EOR.create(var1);
         }
      }

      return null;
   }

   static class eo {
      public eo() {
      }

      public boolean q(IEGeneric var1) {
         if (!EUtil.isOperation(var1, OperationType.ADD, OperationType.SUB)) {
            return false;
         } else {
            IEOperation var2 = var1.asOperation();
            IEGeneric var3 = var2.getOperand1();
            IEGeneric var4 = var2.getOperand2();
            if (!(var3 instanceof IEOperation) && !(var4 instanceof IEOperation)) {
               return false;
            } else {
               atn.eo.eo var5 = new atn.eo.eo();
               if (var5.q(var3)) {
                  atn.eo.eo var6 = new atn.eo.eo();
                  if (var6.q(var4)) {
                     IEImm var7 = var5.q(var2);
                     IEImm var8 = var6.q(var2);
                     IEImm var9 = var7._add(var8);
                     byte var10;
                     if (var5.q()) {
                        var10 = 1;
                     } else {
                        if (!var6.q()) {
                           return false;
                        }

                        var10 = 2;
                     }

                     if (var10 == 1) {
                        var5.RF();
                        var6.q(var9);
                     } else {
                        var6.RF();
                        var5.q(var9);
                     }

                     return true;
                  }
               }

               return false;
            }
         }
      }

      private static class eo {
         private List q = new ArrayList();
         private IEImm RF;
         private int xK = -1;

         private boolean q(IEGeneric var1) {
            if (var1 instanceof IEOperation) {
               IEOperation var2 = var1.asOperation();
               OperationType var3 = var2.getOperationType();
               if (var3 == OperationType.ADD || var3 == OperationType.SUB) {
                  this.q.add(var2);
                  IEGeneric var4 = var2.getOperand1();
                  if (this.q(var4)) {
                     return true;
                  }

                  IEGeneric var5 = var2.getOperand2();
                  if (this.q(var5)) {
                     return true;
                  }

                  this.q.remove(this.q.size() - 1);
               }
            } else if (var1 instanceof IEImm) {
               this.RF = var1.asImm();
               return true;
            }

            return false;
         }

         private IEImm q(IEOperation var1) {
            if (this.RF != null && this.xK < 0) {
               this.xK = 0;
               this.q.add(0, var1);
               Object var2 = this.RF;

               for (int var3 = this.q.size() - 1; var3 >= 0; var3--) {
                  IEOperation var4 = (IEOperation)this.q.get(var3);
                  if (var4.getOperationType() != OperationType.ADD && var4.getOperand1() != var2) {
                     this.xK++;
                  }

                  var2 = var4;
               }

               return (this.xK & 1) == 1 ? this.RF._neg() : this.RF;
            } else {
               throw new IllegalArgumentException();
            }
         }

         private boolean q() {
            if (this.q.size() < 2) {
               return false;
            } else {
               int var1 = this.q.size() - 1;
               IEOperation var2 = (IEOperation)this.q.get(var1);
               if (this.RF == var2.getOperand2()) {
                  return true;
               } else {
                  if (var2.getOperationType() == OperationType.SUB) {
                     IEOperation var3 = (IEOperation)this.q.get(var1 - 1);
                     if (var3.getOperand1() == var2) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }

         private boolean RF() {
            int var1 = this.q.size() - 1;
            IEOperation var2 = (IEOperation)this.q.get(var1);
            IEOperation var3 = (IEOperation)this.q.get(var1 - 1);
            if (this.RF == var2.getOperand2()) {
               IEGeneric var5 = var2.getOperand1();
               return var3.replaceSubExpression(var2, var5);
            } else {
               IEGeneric var4 = var2.getOperand2();
               if (var2.getOperationType() == OperationType.SUB) {
                  if (var3.getOperand1() == var2) {
                     return false;
                  } else if (!var3.replaceSubExpression(var2, var4)) {
                     return false;
                  } else {
                     ((amf)var3).q(var3.getOperationType() == OperationType.ADD ? OperationType.SUB : OperationType.ADD);
                     return true;
                  }
               } else {
                  return var3.replaceSubExpression(var2, var4);
               }
            }
         }

         private boolean q(IEImm var1) {
            int var2 = this.q.size() - 1;
            IEOperation var3 = (IEOperation)this.q.get(var2);
            boolean var4;
            if ((this.xK & 1) == 0) {
               var4 = var3.replaceSubExpression(this.RF, var1);
            } else {
               var4 = var3.replaceSubExpression(this.RF, var1._neg());
            }

            Assert.a(var4);
            return true;
         }
      }
   }
}
