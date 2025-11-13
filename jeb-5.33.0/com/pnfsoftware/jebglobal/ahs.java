package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import java.util.HashMap;
import java.util.Map;

public class ahs extends AbstractCOptimizer {
   @Override
   protected int perform() {
      int var1 = 0;
      HashMap var2 = new HashMap();
      var1 += this.pC(this.m.getBody(), var2);
      return var1 + this.A(this.m.getBody(), var2);
   }

   private int pC(ICBlock var1, Map var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         ICStatement var5 = var1.get(var4);
         if (var5 instanceof ICCompound) {
            for (ICBlock var7 : ((ICCompound)var5).getBlocks()) {
               var3 += this.pC(var7, var2);
            }
         }

         if (var5 instanceof ICLabel) {
            var10 = null;

            int var11;
            for (var11 = var4 + 1; var11 < var1.size(); var11++) {
               if (!(var1.get(var11) instanceof ICLabel var10)) {
                  break;
               }
            }

            if (var10 != null) {
               int var12 = var11 - var4 - 1;

               for (int var9 = 0; var9 < var12; var9++) {
                  var2.put((ICLabel)var1.get(var4), var10);
                  var1.remove(var4);
               }

               var3 += var12;
            }
         }
      }

      return var3;
   }

   private int A(ICBlock var1, Map var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         ICStatement var5 = var1.get(var4);
         if (var5 instanceof ICCompound) {
            for (ICBlock var7 : ((ICCompound)var5).getBlocks()) {
               var3 += this.A(var7, var2);
            }
         }

         if (var5 instanceof ICGoto var9) {
            ICLabel var10 = var9.getLabel();
            if (var2.containsKey(var10)) {
               ICLabel var8 = (ICLabel)var2.get(var10);
               var9.setLabel(var8);
               var3++;
            }
         }
      }

      return var3;
   }
}
