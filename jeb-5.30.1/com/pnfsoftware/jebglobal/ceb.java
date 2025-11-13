package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.HashSet;
import java.util.Set;

public class ceb extends cax {
   private Set Dw = new HashSet();

   public ceb() {
      this.q = false;
   }

   @Override
   public bvx q(IDExpression var1) {
      IDExpression var2 = null;
      if (var1 instanceof IDInstruction var3 && var3.isJcond()) {
         var2 = var3.getJcondCondition();
      }

      if (var1 instanceof IDOperation var9 && var9.isConditional()) {
         var2 = var9.getCondPredicate();
      }

      if (var2 != null) {
         String var10 = var2.toString();
         if (this.Dw.add(var10)) {
            Set var4 = bto.Uv(var2);
            if (var4.size() == 1) {
               IDVar var5 = (IDVar)var4.iterator().next();
               if (bwb.q(var5)) {
                  bwb var6 = new bwb(this.g, var2, var5);
                  if (var6.xK()) {
                     IDExpression var7 = var6.gO();
                     if (var7 != null
                        && var7 != var2
                        && DUtil.calculateComplexity(var7) < DUtil.calculateComplexity(var2)
                        && var1.replaceSubExpression(var2, var7)) {
                        Set var8 = bto.Uv(var7);
                        return bvx.q(var1, !var8.equals(var4));
                     }
                  }
               }
            }
         }
      }

      return null;
   }
}
