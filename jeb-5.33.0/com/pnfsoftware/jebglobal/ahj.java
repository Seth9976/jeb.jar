package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import java.util.List;

public class ahj extends AbstractCOptimizer {
   @Override
   protected int perform() {
      int var1 = 0;
      List var2 = this.m.toFlatList();

      for (int var3 = 0; var3 < var2.size(); var3++) {
         ICStatement var4 = (ICStatement)var2.get(var3);
         if (var4 instanceof ICGoto) {
            ICLabel var5 = ((ICGoto)var4).getLabel();

            for (int var6 = 0; var6 < var2.size() - 1; var6++) {
               ICStatement var7 = (ICStatement)var2.get(var6);
               if (var7 == var5) {
                  ICStatement var8 = (ICStatement)var2.get(var6 + 1);
                  if (!(var8 instanceof ICReturn)) {
                     break;
                  }

                  ICReturn var9 = (ICReturn)var8.duplicate();
                  ICExpression var10 = ((ICReturn)var8).getExpression();
                  if (var10 == null || var10 instanceof ICConstant || var10 instanceof ICIdentifier) {
                     var2.set(var3, var9);
                     var1++;
                  }
                  break;
               }
            }
         }
      }

      if (var1 > 0) {
         this.m.fromFlatList(var2);
      }

      return var1;
   }
}
