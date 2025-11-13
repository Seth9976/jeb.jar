package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICControlBreaker;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ahv extends AbstractCOptimizer {
   @Override
   protected int perform() {
      int var1 = 0;
      HashMap var2 = new HashMap();
      HashSet var3 = new HashSet();
      List var4 = this.m.getStatements();

      for (int var5 = 0; var5 < var4.size(); var5++) {
         ICStatement var6 = (ICStatement)var4.get(var5);
         ICLabel var7;
         if (var6 instanceof ICControlBreaker && (var7 = ((ICControlBreaker)var6).getLabel()) != null) {
            var2.put((ICControlBreaker)var6, var7);
         } else if (var6 instanceof ICLabel) {
            var3.add((ICLabel)var6);
         }
      }

      for (ICLabel var10 : var3) {
         if (!var2.values().contains(var10)) {
            this.m.deleteStatement(var10);
            var1++;
         }
      }

      for (ICControlBreaker var11 : var2.keySet()) {
         ICLabel var12 = (ICLabel)var2.get(var11);
         if (var12 != null && !var3.contains(var12)) {
            this.m.deleteStatement(var11);
            var1++;
         }
      }

      return var1;
   }
}
