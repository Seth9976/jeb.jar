package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;

public class cfj extends AbstractDOptimizer {
   public cfj() {
      this.addTag("slow");
      this.addTag("deobfuscator");
      bto.RF(this);
   }

   @Override
   public int perform() {
      this.analyzeChains(false);
      int var2 = this.dfa.setMaxBlocks(30);

      int var1;
      try {
         var1 = this.q();
      } finally {
         this.dfa.setMaxBlocks(var2);
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private int q() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         int var4 = 0;

         while (var4 < var3.size()) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            IDElement var6 = var5.getOperand1();
            IDElement var7 = var5.getOperand2();
            if (var5.getOpcode() == DOpcodeType.IR_ASSIGN && var6 instanceof IDVar && var7 instanceof IDImm) {
               IDVar var8 = (IDVar)var6;
               this.analyzeChains(false);
               Collection var9 = this.dfa.getReachChains(var3, var4, var8.getId());
               if (var9 != null && !var9.isEmpty()) {
                  int var10 = 0;

                  for (long var12 : var9) {
                     IDInstruction var14 = (IDInstruction)this.cfg.getInstruction(var12);
                     if (var14 != var5) {
                        if (var14 == null || !var14.equalsEx(var5, false)) {
                           var10 = -1;
                           break;
                        }

                        var10++;
                     }
                  }

                  if (var10 != 1) {
                     var4++;
                  } else {
                     var5.transformToNop();
                     this.dfa.notifyInstructionUpdate(var5.getOffset());
                     var1++;
                     var4++;
                  }
               } else {
                  var4++;
               }
            } else {
               var4++;
            }
         }
      }

      return var1;
   }
}
