package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;

public class cia extends AbstractDOptimizer {
   private static final String q = "Lsome/Type;-><clinit>()V";
   private static final String RF = "Lsome/Type;->sfield2:[I";

   @Override
   public int perform() {
      if (!this.ctx.getMethodSignature().equals("Lsome/Type;-><clinit>()V")) {
         return 0;
      } else if (this.cfg.getInstructionCount() <= 3) {
         return 0;
      } else {
         BasicBlock var1 = this.cfg.get(0);
         IDInstruction var2 = (IDInstruction)var1.get(0);
         if (var2.isAssign() && var2.getAssignDestination() instanceof IDStaticField && var2.getAssignSource() instanceof IDNewArrayInfo) {
            if (var1.size() < 3) {
               if (var2.getSize() < 3) {
                  DUtil.modifyInstructionSizes(this.ctx, var1x -> var1x == var2 ? 3 : null);
               }

               int var3 = var2.getSize();
               IDInstruction var4 = this.ctx.createNop().withOffset(var3 - 2);
               IDInstruction var5 = this.ctx.createNop().withOffset(var3 - 1);
               var2.adjustSize(-2);
               var1.add(1, var4);
               var1.add(2, var5);
            }

            DUtil.splitBlock(this.ctx, var1, 3);
            this.cfg.deleteOutEdges(var1);
            IDInstruction var6 = this.ctx.createAssign(this.g.createStaticField("Lsome/Type;->sfield2:[I"), this.g.createIntArray(new int[]{5, 7, 11, 13}));
            IDInstruction var8 = (IDInstruction)var1.get(1);
            var6.copyBaseFields(var8);
            var1.set(1, var6);
            var6 = this.ctx.createReturn(null);
            IDInstruction var9 = (IDInstruction)var1.get(2);
            var6.copyBaseFields(var9);
            var1.set(2, var6);
            this.cleanGraph();
            return 1;
         } else {
            return 0;
         }
      }
   }
}
