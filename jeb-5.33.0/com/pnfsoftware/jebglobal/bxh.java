package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import java.util.HashSet;
import java.util.Set;

public class bxh extends AbstractDOptimizer {
   private static final Set pC = new HashSet();

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.isInvoke() && var5.getInvokeData() instanceof IDCallInfo) {
               IDCallInfo var6 = var5.getInvokeData().asCallInfo();
               String var7 = var6.getMethodSignature();
               if (pC.contains(var7)) {
                  IDExpression var8 = var6.getArgument(0);
                  if (var8 instanceof IDCallInfo) {
                     var5.setOperand2(var8.asCallInfo());
                     var1++;
                  }
               }
            }
         }
      }

      return var1;
   }

   static {
      pC.add("Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;");
      pC.add("Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;");
      pC.add("Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;");
      pC.add("Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;");
      pC.add("Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;");
      pC.add("Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;");
      pC.add("Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;");
      pC.add("Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;");
   }
}
