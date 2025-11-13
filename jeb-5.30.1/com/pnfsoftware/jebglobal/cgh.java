package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;

public class cgh extends AbstractDOptimizer {
   @Override
   public int perform() {
      HashSet var1 = new HashSet();

      for (BasicBlock var3 : this.cfg) {
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (var4.isJcondOrSwitch()) {
            var4.collectVarIds(var1);
         }
      }

      if (var1.isEmpty()) {
         return 0;
      } else {
         int var8 = 0;

         for (int var10 : var1) {
            IDVar var5 = this.ctx.getVar(var10);
            if (var5 == null) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException(Strings.ff("Expected var for id 0x%X", var10)), this.ctx.getMethodSignature());
            } else if (bwq.q(var5)) {
               bwq var6 = new bwq(this.ctx, var5);
               int var7 = var6.q(true);
               if (var7 > 0) {
                  var8++;
               }
            }
         }

         return var8;
      }
   }
}
