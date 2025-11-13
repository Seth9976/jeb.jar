package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import java.util.ArrayList;
import java.util.List;

public class axg extends aws {
   List pC = new ArrayList();

   @Override
   public void pC(auu var1) {
      ILabelManager var2 = var1.E().ld().getLabelManager();
      long var3 = var1.E().getCFG().getFirstAddress();

      for (BasicBlock var6 : var1.E().getCFG().getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            String var9 = var2.getLabel(var8.getOffset(), 0L, AutoLabelPolicy.OFF);
            if (var9 != null) {
               long var10 = var8.getOffset() - var3;
               if (var10 != 0L) {
                  this.pC.add(new axf(var10, var9));
               }
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
