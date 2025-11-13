package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;

public class ajh extends AbstractCOptimizer {
   private static final StructuredLogger q = aeg.q(ajh.class);

   @Override
   protected int perform() {
      int var1 = 0;
      ahv var2 = new ahv(this.m);
      List var3 = var2.q();

      for (int var4 = 0; var4 < var3.size(); var4++) {
         ICStatement var5 = (ICStatement)var3.get(var4);
         if (var5 instanceof ahx) {
            int var6 = var2.xK(var4) + 1;
            var2.q(var6, true, false, 2, null);
            ArrayList var7 = new ArrayList(var2.xK());

            for (int var8 = var4 + 1; var8 < var6; var8++) {
               ICStatement var9 = (ICStatement)var3.get(var8);
               if (var9 instanceof ahx || var9 instanceof aib || var9 instanceof ahn || var9 instanceof ahp) {
                  var8 = var2.xK(var8);
               } else if (var9 instanceof ICGoto) {
                  int var10 = var2.q(var8, true, false, 2, null);
                  if (var10 == var6 || var7.contains(var10)) {
                     var3.set(var8, this.ef.createBreak(null));
                     var1++;
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.m.fromFlatList(var3);
      }

      return var1;
   }
}
