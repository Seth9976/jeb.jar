package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ain extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size() - 1) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICAssignment && ((ICAssignment)var4).isSimpleAssignment()) {
            ICAssignment var5 = (ICAssignment)var4;
            ICLeftExpression var6 = var5.getLeft();
            ICIdentifier var7 = null;
            if (var6 instanceof ICIdentifier) {
               var7 = (ICIdentifier)var6;
            } else if (var6 instanceof ICDecl) {
               var7 = ((ICDecl)var6).getIdentifier();
            }

            if (var7 != null && var7.getIdentifierClass().isLocal()) {
               ICStatement var8 = var1.get(var3 + 1);
               if (var8 instanceof ICReturn && ((ICReturn)var8).getExpression() == var7) {
                  ICExpression var9 = ((ICAssignment)var4).getRight();
                  var1.remove(var3);
                  ((ICReturn)var8).setExpression(var9);
                  var2++;
                  continue;
               }
            }
         }

         var3++;
      }

      return var2;
   }
}
