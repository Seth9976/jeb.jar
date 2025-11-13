package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;

public class cff extends AbstractDOptimizer {
   public cff() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      int var1 = 0;

      label75:
      for (IDInstruction var3 : this.cfg.instructions()) {
         if (var3.isInvoke() && var3.getInvokeData() instanceof IDCallInfo var4) {
            String var9 = var4.getMethodSignature();
            if (bto.RF(this.g, var9) && q(var9)) {
               for (IDExpression var7 : var4.getArguments()) {
                  if (var7.hasSideEffects(this.ctx, true)) {
                     continue label75;
                  }
               }

               for (int var10 = var4.hasThis() ? 1 : 0; var10 < var4.getCountOfArguments() - 1; var10++) {
                  IDExpression var11 = var4.getArgument(var10);
                  if (!bto.gO(var11)) {
                     continue label75;
                  }
               }

               if (var4.getArgument(var4.getCountOfArguments() - 1) instanceof IDNewArrayInfo var8
                  && var8.getSize().isConstantImm(1L)
                  && var8.getCountOfInitialValues() == 0) {
                  var3.transformToNop();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   static boolean q(String var0) {
      if (!var0.endsWith(")V")) {
         return false;
      } else {
         JvmMethodSig var1 = JvmMethodSig.parse(var0);
         int var2 = var1.partypes.size();
         if (var2 < 2) {
            return false;
         } else {
            String var3 = (String)var1.partypes.get(var2 - 1);
            if (!var3.equals("[Ljava/lang/Object;") && !var3.equals("[Ljava/lang/String;")) {
               return false;
            } else {
               boolean var4 = false;

               for (int var5 = 0; var5 < var2 - 1; var5++) {
                  var3 = (String)var1.partypes.get(var5);
                  int var6 = RF(var3);
                  if (var6 == 1) {
                     var4 = true;
                  } else if (var6 != 2 && var6 != 3) {
                     return false;
                  }
               }

               return var4;
            }
         }
      }
   }

   static int RF(String var0) {
      switch (var0) {
         case "Z":
         case "B":
         case "C":
         case "S":
         case "I":
         case "J":
         case "F":
         case "D":
            return 1;
         case "[Z":
         case "[B":
         case "[C":
         case "[S":
         case "[I":
         case "[J":
         case "[F":
         case "[D":
            return 2;
         case "Ljava/lang/String;":
            return 3;
         default:
            return 0;
      }
   }
}
