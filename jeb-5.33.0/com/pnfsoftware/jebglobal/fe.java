package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraphManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class fe implements ICallGraphManager {
   @SerId(1)
   private ICallGraph pC;

   public fe(Tw var1) {
      this.pC = new bj(var1);
   }

   public void pC(ICallGraph var1) {
      this.pC = var1;
   }

   @Override
   public ICallGraph getGlobalCallGraph() {
      return this.pC;
   }
}
