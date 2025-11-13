package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IControlFlowGraph;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.util.base.Assert;

class DUtil$8 extends CFGUtil.DotFileGenerator {
   DUtil$8(IControlFlowGraph var1, IDTryData var2, IDMethodContext var3) {
      super(var1);
      this.val$exdata = var2;
      this.val$ctx = var3;
   }

   @Override
   protected String generateIrregularEdgeLabel(IBasicBlock var1, int var2) {
      try {
         IDExceptionHandler var3 = (IDExceptionHandler)this.val$exdata.getBlockHandlers((int)var1.getBase()).get(var2);
         Assert.a(var3.getAddress() == (int)var1.getIrregularOutputBlock(var2).getBase());
         int var4 = var3.getTypeIndex();
         if (var4 < 0) {
            return "*";
         } else {
            IDexType var5 = this.val$ctx.getDex().getType(var4);
            String var6 = var5.getSignature(true, false, false);
            if (var6.endsWith("Exception")) {
               var6 = var6.substring(0, var6.length() - 9);
            }

            return var6;
         }
      } catch (Exception var7) {
         return null;
      }
   }

   @Override
   protected String generateNodeBackgroundColor(IBasicBlock var1) {
      IDInstruction var2 = (IDInstruction)var1.getLast();
      return Boolean.TRUE.equals(var2.getData("KEEP_INSTRUCTION")) ? "orange" : null;
   }
}
