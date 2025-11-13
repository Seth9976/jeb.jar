package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger64;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class agp extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 1;

      while (var3 < var1.size()) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICReturn && !((ICReturn)var4).returnsVoid() && var1.get(var3 - 1) instanceof ICAssignment) {
            ICAssignment var5 = (ICAssignment)var1.get(var3 - 1);
            ICLeftExpression var6 = var5.getLeft();
            ICIdentifier var7 = null;
            if (var6 instanceof ICIdentifier) {
               var7 = (ICIdentifier)var6;
            } else if (var6 instanceof ICDecl) {
               var7 = ((ICDecl)var6).getIdentifier();
            }

            ICExpression var8 = ((ICReturn)var4).getExpression();
            if (var7 != null && var7 == var8) {
               Object var9 = var5.getRight();
               if (var5.isUnaryOperatorAssignment()) {
                  String var11 = var7.getType().getSignature();
                  switch (var11) {
                     case "int":
                        ICConstantInteger32 var13 = this.m.getGlobalContext().getConstantFactory().createInt32(1);
                        var9 = CUtil.add(this.m, var7, var13);
                        break;
                     case "long":
                        ICConstantInteger64 var10 = this.m.getGlobalContext().getConstantFactory().createInt64(1L);
                        var9 = CUtil.add(this.m, var7, var10);
                        break;
                     default:
                        var9 = null;
                  }
               } else if (var5.isCombinedOperatorAssignment()) {
                  var9 = this.ef.createOperation(var5.getCombinedOperator(), var7, (ICExpression)var9);
               } else if (!var5.isSimpleAssignment()) {
                  var9 = null;
               }

               if (var9 != null && var7.getIdentifierClass().isLocal()) {
                  ((ICReturn)var4).setExpression((ICExpression)var9);
                  var1.remove(var3 - 1);
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
