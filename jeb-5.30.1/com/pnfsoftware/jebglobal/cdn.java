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

public class cdn extends AbstractDOptimizer {
   static final Set q = new HashSet();

   public cdn() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
      this.addTag("deobfuscator");
      bto.Dw(this);
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else {
         if (this.cfg.size() == 1) {
            BasicBlock var1 = this.cfg.get(0);
            if (var1.size() == 1) {
               IDInstruction var2 = (IDInstruction)var1.get(0);
               if (var2.isReturn()) {
                  IDExpression var3 = var2.getReturnExpression();
                  if (var3 != null) {
                     if (var3 instanceof IDCallInfo var4 && q.contains(var4.getMethodSignature())) {
                        var3 = var4.getArgument(0);
                     }

                     if (var3 instanceof IDOperation var6 && var6.getOperator().isCast()) {
                        var3 = var6.getOperand2();
                     }

                     if (var3 instanceof IDInvokeInfo var7 && this.q(var2, var7)) {
                        return 1;
                     }
                  }
               }
            } else if (var1.size() == 2 && ((IDInstruction)var1.get(1)).isReturn() && ((IDInstruction)var1.get(1)).getReturnExpression() == null) {
               IDInstruction var5 = (IDInstruction)var1.get(0);
               if (var5.isInvoke() && this.q(var5, var5.getInvokeData())) {
                  return 1;
               }
            }
         }

         return 0;
      }
   }

   private boolean q(IDInstruction var1, IDInvokeInfo var2) {
      if (!(var2 instanceof IDCallInfo var3)) {
         return false;
      } else if (!(var3 instanceof IDNewInfo) && !var3.getMethodName().equals("<init>")) {
         IDexMethod var4 = this.dex.getMethod(var3.getMethodSignature());
         if (var4 != null && var4.getData() != null && var4.getData().getCodeItem() != null) {
            bun var5 = bun.q(var3.getMethodSignature(), this.g);
            if (var5 != null && var5 != bun.q) {
               int var6 = 0;
               if (!this.ctx.isStaticMethod()) {
                  IDVar var7 = (IDVar)this.ctx.getParameterVariables().get(0);
                  if (var3.getCountOfArguments() > 0
                     && var3.getArgument(0) instanceof IDNewArrayInfo var9
                     && var9.getCountOfInitialValues() >= 1
                     && var9.getInitialValue(0) == var7) {
                     if (this.q(var3)) {
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
                  bvv var10 = new bvv(this.ctx);
                  if (!var10.q(var1, var3)) {
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

   private boolean q(IDCallInfo var1) {
      for (IDExpression var3 : var1.getArguments()) {
         if (var3.isConstantImm()) {
            return true;
         }
      }

      return false;
   }

   static {
      q.add(
         cvm.q(new byte[]{-1, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 97, 59, 24, 15, 7, 23, 73, 22, 19, 92, 27, 13, 17, 51, 55, 13, 25, 16, 77, 1, 107}, 1, 179)
      );
      q.add(
         cvm.q(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 65, 88, 93, 70, 88, 118, 46, 30, 20, 6, 68, 76, 115}, 2, 229
         )
      );
      q.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 91, 94, 70, 98, 77, 76, 58, 23, 73, 74, 37}, 2, 144));
      q.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 94, 95, 92, 83, 122, 65, 35, 7, 4, 75, 69, 47}, 2, 56));
      q.add(
         cvm.q(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 84, 92, 93, 85, 88, 118, 46, 30, 20, 6, 68, 76, 102}, 2, 154
         )
      );
      q.add(
         cvm.q(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 45, 92, 77, 83, 92, 75, 8, 1, 30, 86, 95, 71, 86, 64, 69, 25, 19, 13, 22, 9, 77, 9, 37}, 2, 156
         )
      );
      q.add(
         cvm.q(
            new byte[]{124, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 109, 45, 0, 3, 9, 4, 15, 85, 22, 19, 92, 13, 0, 3, 9, 4, 15, 56, 55, 13, 25, 16, 77, 1, 115},
            1,
            48
         )
      );
      q.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 80, 84, 84, 2, 20, 13, 78, 89, 70, 85, 100, 85, 64, 85, 42, 90, 72, 33}, 2, 136));
      q.add(
         cvm.q(
            new byte[]{29, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 43, 9, 19, 19, 2, 23, 17, 23, 73, 22, 19, 93, 11, 9, 19, 36, 55, 13, 25, 16, 77, 1, 106},
            1,
            81
         )
      );
      q.add(
         cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 48, 65, 79, 67, 77, 2, 30, 18, 83, 90, 95, 64, 64, 122, 65, 35, 7, 4, 75, 69, 54}, 2, 155)
      );
      q.add(
         cvm.q(new byte[]{-104, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 102, 39, 26, 17, 2, 2, 23, 73, 22, 19, 87, 7, 26, 34, 55, 13, 25, 16, 77, 1, 96}, 1, 212)
      );
      q.add(cvm.q(new byte[]{65, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 99, 35, 1, 9, 92, 22, 19, 82, 3, 1, 9, 49, 55, 13, 25, 16, 77, 1, 99}, 1, 13));
      q.add(
         cvm.q(new byte[]{16, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 105, 42, 3, 14, 21, 79, 22, 19, 88, 10, 3, 14, 21, 34, 55, 13, 25, 16, 77, 1, 111}, 1, 92)
      );
      q.add(
         cvm.q(
            new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 39, 70, 85, 83, 85, 92, 8, 1, 30, 86, 95, 71, 86, 64, 69, 25, 19, 13, 22, 9, 77, 9, 37}, 2, 186
         )
      );
   }
}
