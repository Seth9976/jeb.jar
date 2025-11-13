package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class cav extends AbstractDOptimizer {
   @Override
   public int perform() {
      byte var1 = 0;
      cav.Av var2 = new cav.Av();

      for (IDInstruction var4 : this.cfg.instructions()) {
         var4.visitInstructionPostOrder(var2, true);
      }

      return var1 + var2.pC;
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4 && var4.getInvokeType() == DInvokeType.STATIC && var4.getCountOfArguments() >= 1) {
            String var5 = var4.getMethodSignature();
            switch (var5) {
               case "Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;":
                  IDExpression var13 = var4.getArgument(0);
                  if (var13.isConstantImm()) {
                     boolean var14 = var13.asImm().getRawValue() != 0L;
                     String var15 = "Ljava/lang/Boolean;->" + (var14 ? "TRUE" : "FALSE") + ":Ljava/lang/Boolean;";
                     IDStaticField var17 = cav.this.g.createStaticField(var15, true);
                     if (var2.replaceSubExpression(var1, var17)) {
                        this.pC++;
                     }
                  }
                  break;
               case "Ljava/lang/Boolean;->valueOf(Ljava/lang/String;)Ljava/lang/Boolean;":
                  if (var4.getArgument(0) instanceof IDImm var9) {
                     Boolean var10 = null;
                     if (var9.isNullRef()) {
                        var10 = false;
                     } else if (var9.isString()) {
                        String var11 = var9.getStringValue(cav.this.g);
                        var10 = var11 != null && var11.equalsIgnoreCase("true");
                     }

                     if (var10 != null) {
                        String var16 = "Ljava/lang/Boolean;->" + (var10 ? "TRUE" : "FALSE") + ":Ljava/lang/Boolean;";
                        IDStaticField var12 = cav.this.g.createStaticField(var16, true);
                        if (var2.replaceSubExpression(var1, var12)) {
                           this.pC++;
                        }
                     }
                  }
            }
         }
      }
   }
}
