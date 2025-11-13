package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class chd extends AbstractDOptimizer {
   private static final String q = "Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;";
   private int RF;

   @Override
   public int perform() {
      this.RF = 0;

      for (int var1 = 0; var1 < this.cfg.size(); var1++) {
         BasicBlock var2 = this.cfg.get(var1);

         for (int var3 = 0; var3 < var2.size(); var3++) {
            IDInstruction var4 = (IDInstruction)var2.get(var3);
            var4.visitDepthPost(new che(this));
         }
      }

      return this.RF;
   }
}
