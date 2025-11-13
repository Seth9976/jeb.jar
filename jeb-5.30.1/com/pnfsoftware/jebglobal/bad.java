package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import java.util.ArrayList;
import java.util.List;

public class bad extends azp {
   List q = new ArrayList();

   @Override
   public void q(axp var1) {
      ILabelManager var2 = var1.oW().gP().getLabelManager();
      long var3 = var1.oW().getCFG().getFirstAddress();

      for (BasicBlock var6 : var1.oW().getCFG().getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            String var9 = var2.getLabel(var8.getOffset(), 0L, AutoLabelPolicy.OFF);
            if (var9 != null) {
               long var10 = var8.getOffset() - var3;
               if (var10 != 0L) {
                  this.q.add(new bac(var10, var9));
               }
            }
         }
      }
   }

   @Override
   public List q() {
      return this.q;
   }

   @Override
   public void RF() {
      this.q.clear();
   }
}
