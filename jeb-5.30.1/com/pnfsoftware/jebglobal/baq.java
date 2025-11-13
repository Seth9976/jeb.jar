package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import java.util.ArrayList;
import java.util.List;

public class baq extends baf {
   private List q = new ArrayList();

   @Override
   public void q(INativeMethodItem var1) {
      if (var1.getData() == null) {
         throw new IllegalArgumentException("Only internal routines can be signed");
      } else if (this.q.size() != 0) {
         throw new IllegalStateException("Signer state must be cleared first");
      } else {
         this.q.add(new bap(var1.getData().getCFG().getInstructionCount()));
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
