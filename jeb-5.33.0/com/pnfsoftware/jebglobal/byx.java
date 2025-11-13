package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.HashSet;
import java.util.Set;

public class byx extends AbstractDOptimizer {
   static final Set pC = new HashSet();

   public byx() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else {
         if (this.cfg.size() == 1) {
            BasicBlock var1 = this.cfg.get(0);
            if (var1.size() == 1) {
               IDInstruction var2 = (IDInstruction)var1.get(0);
               if (var2.isReturn()) {
                  IDExpression var3 = var2.getReturnExpression();
                  if (var3 != null) {
                     if (var3 instanceof IDCallInfo var4 && pC.contains(var4.getMethodSignature())) {
                        var3 = var4.getArgument(0);
                     }

                     if (var3 instanceof IDOperation var6 && var6.getOperator().isCast()) {
                        var3 = var6.getOperand2();
                     }

                     if (var3 instanceof IDInvokeInfo var7 && this.pC(var2, var7)) {
                        return 1;
                     }
                  }
               }
            } else if (var1.size() == 2 && ((IDInstruction)var1.get(1)).isReturn() && ((IDInstruction)var1.get(1)).getReturnExpression() == null) {
               IDInstruction var5 = (IDInstruction)var1.get(0);
               if (var5.isInvoke() && this.pC(var5, var5.getInvokeData())) {
                  return 1;
               }
            }
         }

         return 0;
      }
   }

   private boolean pC(IDInstruction var1, IDInvokeInfo var2) {
      if (!(var2 instanceof IDCallInfo var3)) {
         return false;
      } else if (!(var3 instanceof IDNewInfo) && !var3.getMethodName().equals("<init>")) {
         IDexMethod var4 = this.dex.getMethod(var3.getMethodSignature());
         if (var4 != null && var4.getData() != null && var4.getData().getCodeItem() != null) {
            bqh var5 = bqh.pC(var3.getMethodSignature(), this.g);
            if (var5 != null && var5 != bqh.pC) {
               int var6 = 0;
               if (!this.ctx.isStaticMethod()) {
                  IDVar var7 = (IDVar)this.ctx.getParameterVariables().get(0);
                  if (var3.getCountOfArguments() > 0
                     && var3.getArgument(0) instanceof IDNewArrayInfo var9
                     && var9.getCountOfInitialValues() >= 1
                     && var9.getInitialValue(0) == var7) {
                     if (this.pC(var3)) {
                        var6++;
                     }

                     if (var4.getData().isPrivate() || var4.getData().isStatic()) {
                        var6++;
                     }
                  }
               }

               if (var6 < 1) {
                  return false;
               } else {
                  brm var10 = new brm(this.ctx);
                  if (!var10.pC(var1, var3)) {
                     return false;
                  } else {
                     if (var6 >= 2 && var4.getData().getInlineMode() != 3) {
                        var4.getData().setInlineMode(3);
                     }

                     return true;
                  }
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean pC(IDCallInfo var1) {
      for (IDExpression var3 : var1.getArguments()) {
         if (var3.isConstantImm()) {
            return true;
         }
      }

      return false;
   }

   static {
      pC.add(
         ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 80, 73, 70, 80, 122, 65, 35, 7, 4, 75, 69, 39}, 2, 38)
      );
      pC.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 65, 88, 93, 71, 88, 118, 46, 30, 20, 6, 68, 76, 115}, 2, 236
         )
      );
      pC.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 91, 94, 70, 99, 77, 76, 58, 23, 73, 74, 37}, 2, 98));
      pC.add(
         ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 94, 95, 92, 82, 122, 65, 35, 7, 4, 75, 69, 47}, 2, 233)
      );
      pC.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 84, 92, 93, 84, 88, 118, 46, 30, 20, 6, 68, 76, 102}, 2, 246
         )
      );
      pC.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 86, 95, 71, 87, 64, 69, 25, 19, 13, 22, 9, 77, 9, 37}, 2, 64
         )
      );
      pC.add(
         ckx.pC(
            new byte[]{
               15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 70, 79, 93, 92, 88, 93, 23, 13, 12, 82, 93, 90, 64, 69, 46, 28, 55, 2, 0, 16, 69, 73, 71, 62
            },
            2,
            220
         )
      );
      pC.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 80, 84, 84, 2, 20, 13, 78, 89, 70, 85, 100, 84, 64, 85, 42, 90, 72, 33}, 2, 224));
      pC.add(
         ckx.pC(
            new byte[]{61, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 43, 9, 19, 19, 2, 23, 17, 23, 73, 22, 19, 93, 11, 9, 19, 36, 55, 13, 25, 16, 77, 1, 106},
            1,
            113
         )
      );
      pC.add(
         ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 48, 65, 79, 67, 77, 2, 30, 18, 83, 90, 95, 64, 65, 122, 65, 35, 7, 4, 75, 69, 54}, 2, 195)
      );
      pC.add(
         ckx.pC(new byte[]{98, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 102, 39, 26, 17, 2, 2, 23, 73, 22, 19, 87, 7, 26, 34, 55, 13, 25, 16, 77, 1, 96}, 1, 46)
      );
      pC.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 47, 70, 78, 86, 2, 20, 13, 64, 79, 92, 87, 100, 84, 64, 85, 42, 90, 72, 41}, 2, 244));
      pC.add(
         ckx.pC(
            new byte[]{-101, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 105, 42, 3, 14, 21, 79, 22, 19, 88, 10, 3, 14, 21, 34, 55, 13, 25, 16, 77, 1, 111}, 1, 215
         )
      );
      pC.add(
         ckx.pC(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 39, 70, 85, 83, 85, 92, 8, 1, 30, 86, 95, 71, 87, 64, 69, 25, 19, 13, 22, 9, 77, 9, 37}, 2, 25
         )
      );
   }
}
