package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bzd extends AbstractDOptimizer {
   public bzd() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else if (!bsg.A(this.g)) {
         return 0;
      } else {
         int var1 = 0;

         for (IDInstruction var3 : this.cfg.instructions()) {
            if (var3.isAssignToVar()
               && var3.getAssignSource() instanceof IDCallInfo var4
               && var4.getInvokeType() == DInvokeType.STATIC
               && var4.getMethodSignature().endsWith("I)[Ljava/lang/Object;")) {
               IDexMethod var13 = this.dex.getMethod(var4.getMethodSignature());
               IDexMethodData var6;
               if (var13 != null && (var6 = var13.getData()) != null && var6.getCodeItem() != null && var6.getInlineMode() == 0) {
                  IDVar var7 = (IDVar)var3.getAssignDestination();
                  int var8 = 0;
                  this.analyzeChains();

                  for (long var11 : this.dfa.getDefUses(var3.getOffset(), var7.getId(), 6)) {
                     var8 += this.pC((IDInstruction)this.cfg.getInstruction(var11), var7);
                     if (var8 >= 2) {
                        break;
                     }
                  }

                  if (var8 >= 2 && var6.getInlineMode() != 2) {
                     var6.setInlineMode(2);
                     var1++;
                  }
               }
            }
         }

         return var1;
      }
   }

   private int pC(IDInstruction var1, IDVar var2) {
      if (var1 == null) {
         return 0;
      } else if (var1.isAssignToVar() && this.pC(var1.getAssignSource(), var2)) {
         return 1;
      } else {
         return var1.isJcond()
               && var1.getJcondCondition() instanceof IDOperation var3
               && var3.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
               && this.pC(var3.getOperand1(), var2)
               && this.pC(var3.getOperand2(), var2)
            ? 2
            : 0;
      }
   }

   private boolean pC(IDExpression var1, IDVar var2) {
      return var1 instanceof IDArrayElt var7
         && var7.getIndex() instanceof IDImm var6
         && var6.getRawValue() == 0L
         && var7.getArray() instanceof IDOperation var5
         && var5.isCast(this.tf.createArrayType(this.tf.getInt(), 1))
         && var5.getOperand2() instanceof IDArrayElt var4
         && var4.getArray() == var2
         && var4.getIndex() instanceof IDImm var3
         && var3.getRawValue() <= 10L;
   }
}
