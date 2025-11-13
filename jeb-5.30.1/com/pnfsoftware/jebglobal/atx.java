package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;

public class atx extends AbstractEOptimizer {
   @Override
   protected int perform() {
      HashSet var1 = new HashSet();

      for (BasicBlock var3 : this.cfg) {
         IEStatement var4 = (IEStatement)var3.getLast();
         if (var4 instanceof IEJump || var4 instanceof IESwitch) {
            var1.addAll(var4.getExplicitlyUsed().getVarIds());
         }
      }

      if (var1.isEmpty()) {
         return 0;
      } else {
         int var8 = 0;

         for (int var10 : var1) {
            IEVar var5 = this.ectx.getVariableById(var10);
            if (var5 == null) {
               aeb.q(new RuntimeException(Strings.ff("Expected EVar for id 0x%X", var10)), this.ectx);
            } else {
               apx var6 = new apx(this.ectx);
               int var7 = var6.q(var5, true);
               if (var7 > 0) {
                  var8++;
               }
            }
         }

         return var8;
      }
   }
}
