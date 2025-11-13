package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class cbh extends AbstractDOptimizer {
   boolean pC;

   public cbh() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      int var1 = 0;
      HashSet var2 = new HashSet(this.ctx.getParameterVariables());
      HashMap var3 = new HashMap();
      IDTypeInfoProvider var4 = this.ctx.getTypeInfoProvider();

      for (IDInstruction var6 : this.cfg.instructions()) {
         if (var6.isAssignToVar()) {
            IDVar var7 = var6.getAssignDestination().asVar();
            if (!var2.contains(var7) && var7.getType().isJavaLangObject()) {
               IJavaType var8 = var6.getAssignSource().getType();
               if (var8.isObject() && !var8.isJavaLangObject()) {
                  IJavaType var9 = (IJavaType)var3.put(var7, var8);
                  if (var9 != null && !var9.equals(var8) && !var4.isCompatible(var9.getSignature(), var8.getSignature())) {
                     if (var4.isCompatible(var8.getSignature(), var9.getSignature())) {
                        var3.put(var7, var9);
                     } else {
                        var3.remove(var7);
                        var2.add(var7);
                     }
                  }
               }
            }
         }
      }

      Iterator var11 = var3.keySet().iterator();

      while (true) {
         IDVar var12;
         IJavaType var13;
         while (true) {
            if (!var11.hasNext()) {
               return var1;
            }

            var12 = (IDVar)var11.next();
            var13 = (IJavaType)var3.get(var12);
            if (!this.pC) {
               break;
            }

            cbh.Av var14 = new cbh.Av();
            var14.pC = var4;
            var14.A = var12;
            var14.kS = var13;

            for (IDInstruction var10 : this.cfg.instructions()) {
               if (!var10.visitInstruction(var14)) {
                  break;
               }
            }

            if (var14.wS != 0 && var14.kS != null && !var14.kS.isJavaLangObject()) {
               var13 = var14.kS;
               break;
            }
         }

         if (var13 != var12.getType() && var12.setType(var13, null, true)) {
            var1++;
         }
      }
   }

   class Av implements IDVisitor {
      IDTypeInfoProvider pC;
      IDVar A;
      IJavaType kS;
      int wS;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 == this.A && var2 instanceof IDOperation var4 && var4.isCast()) {
            IJavaType var5 = var4.getType();
            if (this.kS == null) {
               this.kS = var5;
            } else if (!this.kS.equals(var5)) {
               if (!this.pC.isCompatible(this.kS.getSignature(), var5.getSignature())) {
                  this.wS = 0;
                  var3.interrupt(false);
                  return;
               }

               this.kS = var5;
            }

            this.wS++;
         }
      }
   }
}
