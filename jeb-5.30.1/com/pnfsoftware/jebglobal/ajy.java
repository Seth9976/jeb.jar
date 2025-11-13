package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ajy extends AbstractCOptimizer {
   private static final StructuredLogger q = aeg.q(ajy.class);

   @Override
   protected int perform() {
      int var1 = 0;
      ahv var2 = new ahv(this.m);
      ArrayList var3 = new ArrayList();
      HashMap var4 = new HashMap();
      HashMap var5 = new HashMap();
      List var6 = var2.q();

      for (int var7 = 0; var7 < var6.size(); var7++) {
         ICStatement var8 = (ICStatement)var6.get(var7);
         if (var8 instanceof ICGoto) {
            var3.add(var7);
         } else if (var8 instanceof ICLabel) {
            var4.put((ICLabel)var8, var7);
         }
      }

      HashSet var17 = new HashSet();
      var17.add(ICLabel.class);
      ArrayList var18 = new ArrayList();

      for (int var10 : var3) {
         ICGoto var11 = (ICGoto)var6.get(var10);
         ICLabel var12 = var11.getLabel();
         Integer var13 = (Integer)var4.get(var12);
         if (var13 != null) {
            Object var14 = (List)var5.get(var12);
            if (var14 == null) {
               var2.q(var13, true, false, 2, null);
               var14 = new ArrayList(var2.xK());
               var5.put(var12, var14);
            }

            int var15 = var2.q(var10, true, true, 2, null);
            if (var15 > 0) {
               ICStatement var16 = var2.q(var15);
               if (var15 == var13 || var14.contains(var15)) {
                  var18.add(var10);
                  var1++;
               } else if (var16 instanceof ICGoto && ((ICGoto)var16).getLabel() == var12) {
                  var18.add(var10);
                  var1++;
               }
            }
         }
      }

      for (int var20 : Lists.reverse(var18)) {
         var6.remove(var20);
      }

      if (var1 > 0) {
         this.m.fromFlatList(var6);
      }

      return var1;
   }
}
