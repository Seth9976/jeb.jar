package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aka extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICForStm var5) {
            ICStatement var6 = var3 >= 1 ? var1.get(var3 - 1) : null;
            if (var6 != null && var6 instanceof ICDecl var7) {
               ICStatement var8 = var5.getPreStatement();
               ICIdentifier var9 = null;
               ICAssignment var10 = null;
               if (var8 instanceof ICAssignment) {
                  var10 = (ICAssignment)var8;
                  if (var10.getLeft() instanceof ICIdentifier) {
                     var9 = (ICIdentifier)var10.getLeft();
                  }
               }

               if (var9 != null) {
                  boolean var11 = true;

                  for (int var12 = var3 + 1; var12 < var1.size(); var12++) {
                     ICStatement var13 = var1.get(var12);
                     if (afc.RF(var13).contains(var9)) {
                        var11 = false;
                        break;
                     }
                  }

                  if (var11 && var7.getIdentifier() == var9) {
                     var10.setLeft(var7);
                     var1.remove(var6);
                     var2++;
                     var3--;
                  }
               }
            }
         }
      }

      return var2;
   }
}
