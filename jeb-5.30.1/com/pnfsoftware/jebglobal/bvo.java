package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class bvo {
   private IDMethodContext Dw;
   protected int q;
   protected int RF;
   protected int xK;

   public bvo(IDMethodContext var1, int var2, int var3, int var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   protected int q(IDInstruction var1, int var2, int var3, List var4) {
      return 0;
   }

   public List q(IDVar var1, long var2, long var4) {
      CFG var6 = this.Dw.getCfg();
      IDGlobalContext var7 = this.Dw.getGlobalContext();
      HashMap var8 = new HashMap();
      boolean var9 = false;
      ArrayList var10 = new ArrayList();
      int var11 = 0;
      HashMap var12 = new HashMap();

      long var13;
      for (var13 = var2; var13 != var4 && var11 < this.q && var10.size() < this.RF; var11++) {
         IDInstruction var15 = (IDInstruction)var6.getInstruction(var13);
         if (var15 == null) {
            return null;
         }

         Integer var16 = (Integer)var8.compute(var13, (var0, var1x) -> var1x == null ? 1 : var1x + 1);
         int var17 = this.q(var15, var16, var11, var10);
         if (var17 == 0) {
            if (var16 > this.xK) {
               var9 = true;
            }
         } else if (var17 != 1) {
            throw new RuntimeException("Illegal bypass code: " + var17);
         }

         Integer var18 = null;
         if (!var15.isNop()) {
            if (var15.isJump()) {
               var18 = var15.getBranchTarget();
            } else if (var15.isJcondOrSwitch()) {
               Set var19 = var15.getVarIds();
               if (var19.size() != 1 || (Integer)var19.iterator().next() != var1.getId()) {
                  return null;
               }

               try {
                  var18 = var15.evaluate(var12);
               } catch (DexDecEvaluationException var23) {
                  return null;
               }
            } else if (var15.isAssign()) {
               IDExpression var24 = var15.getAssignDestination();
               if (var24.equals(var1)) {
                  IDExpression var20 = var15.getAssignSource();
                  if (DUtil.hasInvokeInfo(var20) || DUtil.usesReferences(var20)) {
                     return null;
                  }

                  try {
                     IDImm var21 = var20.evaluate(var7, var12);
                     var12.put(var1.getId(), var21);
                  } catch (DexDecEvaluationException var22) {
                     return null;
                  }
               }

               if (var9 && var16 >= 2) {
                  return null;
               }

               var10.add(var15);
            } else {
               if (!var15.isInvoke()) {
                  return null;
               }

               if (var9 && var16 >= 2) {
                  return null;
               }

               var10.add(var15);
            }
         }

         if (var18 == null) {
            var18 = (int)var15.getOffsetEnd();
         }

         var13 = var18.intValue();
      }

      return var13 != var4 ? null : var10;
   }
}
