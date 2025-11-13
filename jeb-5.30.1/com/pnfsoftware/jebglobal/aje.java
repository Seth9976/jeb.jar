package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;

public class aje extends AbstractCOptimizer {
   @Override
   protected int perform() {
      ahv var1 = new ahv(this.m);
      return this.q(this.m.getBody(), var1);
   }

   int q(ICBlock var1, ahv var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         ICStatement var5 = var1.get(var4);
         if (var5 instanceof ICGenericLoop) {
            ICLabel var6 = null;
            ICLabel var7 = null;
            ICBlock var8 = null;
            ICStatement var9 = var2.q(var5);
            if (var9 instanceof ICLabel) {
               var6 = (ICLabel)var9;
            }

            if (var5 instanceof ICWhileStm var10) {
               if (var4 >= 1 && var1.get(var4 - 1) instanceof ICLabel) {
                  var7 = (ICLabel)var1.get(var4 - 1);
               } else if (var10.getPredicate().isLitteralTrue() && var10.getBody().size() >= 1 && var10.getBody().get(0) instanceof ICLabel) {
                  var7 = (ICLabel)var10.getBody().get(0);
               }

               var8 = var10.getBody();
            } else if (var5 instanceof ICDoWhileStm var13) {
               if (var13.getBody().size() >= 1 && var13.getBody().getLast() instanceof ICLabel) {
                  var7 = (ICLabel)var13.getBody().getLast();
               } else if (var13.getPredicate().isLitteralTrue() && var13.getBody().size() >= 1 && var13.getBody().get(0) instanceof ICLabel) {
                  var7 = (ICLabel)var13.getBody().get(0);
               }

               var8 = var13.getBody();
            } else if (var5 instanceof ICForStm var14) {
               var8 = var14.getBody();
            }

            if (var6 != null || var7 != null) {
               int var15 = this.q(var8, var8, var6, false, var7);
               if (var15 > 0) {
                  var2 = new ahv(this.m);
               }

               var3 += var15;
            }
         }

         if (var5 instanceof ICCompound) {
            for (ICBlock var12 : ((ICCompound)var5).getBlocks()) {
               var3 += this.q(var12, var2);
            }
         }
      }

      return var3;
   }

   private int q(ICBlock var1, ICBlock var2, ICLabel var3, boolean var4, ICLabel var5) {
      int var6 = 0;
      int var7 = 0;

      for (int var8 = var2.size() - 1; var7 <= var8; var7++) {
         ICStatement var9 = var2.get(var7);
         if (var9 instanceof ICGoto) {
            ICLabel var13 = ((ICGoto)var9).getLabel();
            if (var13 == var3) {
               ICBreak var14 = this.ef.createBreak(var4 ? var5 : null);
               var14.setPhysicalOffsets(var9.getPhysicalOffsets());
               var2.set(var7, var14);
               var6++;
            } else if (var13 == var5) {
               if (var7 == var8 && var2 == var1) {
                  var2.remove(var7);
               } else {
                  var2.set(var7, this.ef.createContinue(null));
               }

               var6++;
            }
         } else if (var9 instanceof ICCompound) {
            boolean var10 = var9 instanceof ICGenericLoop;
            if (!var10) {
               for (ICBlock var12 : ((ICCompound)var9).getBlocks()) {
                  var6 += this.q(var1, var12, var3, var4 || var9 instanceof ICSwitchStm, var5);
               }
            }
         }
      }

      return var6;
   }
}
