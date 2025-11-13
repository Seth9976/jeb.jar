package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import java.util.ArrayList;
import java.util.List;

public class axe extends aws {
   List pC = new ArrayList();

   @Override
   public void pC(auu var1) {
      ICommentManager var2 = var1.E().ld().getCommentManager();
      long var3 = var1.E().getCFG().getFirstAddress();

      for (BasicBlock var6 : var1.E().getCFG().getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            String var9 = var2.getComment(var8.getOffset());
            if (var9 != null) {
               long var10 = var8.getOffset() - var3;
               this.pC.add(new axd(var10, var9));
            }
         }
      }
   }

   @Override
   public List pC() {
      return this.pC;
   }

   @Override
   public void A() {
      this.pC.clear();
   }
}
