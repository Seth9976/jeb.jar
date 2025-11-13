package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import java.util.ArrayList;
import java.util.List;

public class bab extends azp {
   List q = new ArrayList();

   @Override
   public void q(axp var1) {
      ICommentManager var2 = var1.oW().gP().getCommentManager();
      long var3 = var1.oW().getCFG().getFirstAddress();

      for (BasicBlock var6 : var1.oW().getCFG().getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            String var9 = var2.getComment(var8.getOffset());
            if (var9 != null) {
               long var10 = var8.getOffset() - var3;
               this.q.add(new baa(var10, var9));
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
