package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import java.util.ArrayList;
import java.util.List;

public class axs extends axi {
   private List pC = new ArrayList();

   @Override
   public void pC(INativeMethodItem var1) {
      if (var1.getData() == null) {
         throw new IllegalArgumentException("Only internal routines can be signed");
      } else if (this.pC.size() != 0) {
         throw new IllegalStateException("Signer state must be cleared first");
      } else {
         this.pC.add(new axr(var1.getData().getCFG().getInstructionCount()));
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
