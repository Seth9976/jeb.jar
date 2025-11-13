package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraphManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class VA implements ICallGraphManager {
   @SerId(1)
   private ICallGraph q;

   public VA(aaf var1) {
      this.q = new aau(var1);
   }

   public void q(ICallGraph var1) {
      this.q = var1;
   }

   @Override
   public ICallGraph getGlobalCallGraph() {
      return this.q;
   }
}
