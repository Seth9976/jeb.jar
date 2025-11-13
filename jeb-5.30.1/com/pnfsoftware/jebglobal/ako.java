package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCStatementOptimizer;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;

public class ako extends AbstractCStatementOptimizer {
   private int q;

   @Override
   protected ICStatement optimizeStatement(ICStatement var1) {
      this.q = 0;
      IdentityHashSet var2 = new IdentityHashSet();

      for (ICElement var4 : var1.getSubElements()) {
         this.q(var4, var1, var2);
      }

      return this.q == 0 ? null : var1;
   }

   private void q(ICElement var1, ICElement var2, IdentityHashSet var3) {
      if (!var3.contains(var1)) {
         var3.add(var1);
         if (!CUtil.isClassMethodField((ICElement)var1)) {
            if (var1 instanceof ICPredicate && !this.q(var2)) {
               ICExpression var4 = ((ICPredicate)var1).getExpression();
               if (var2.replaceSubElement((ICElement)var1, var4)) {
                  var1 = var4;
                  this.q++;
               }
            }

            for (ICElement var5 : ((ICElement)var1).getSubElements()) {
               this.q(var5, (ICElement)var1, var3);
            }
         }
      }
   }

   private boolean q(ICElement var1) {
      return var1 instanceof ICIfStm || var1 instanceof ICWhileStm || var1 instanceof ICDoWhileStm || var1 instanceof ICForStm;
   }
}
