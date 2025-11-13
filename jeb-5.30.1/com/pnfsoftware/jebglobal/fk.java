package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class fk extends AbstractAnalyzerExtension {
   private static final ILogger q = GlobalLog.getLogger(fk.class);
   @SerTransient
   private hP RF;

   public hP q() {
      if (this.RF == null) {
         this.RF = new hP(this.gca);
      }

      return this.RF;
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      if (var1.size() > 2 && ((fA)var1.get(0).get(0)).getProcessorMode() == 64) {
         return ChainedOperationResult.stop();
      } else {
         BasicBlock var2 = var1.getEntryBlock();
         return var2.isEmpty() ? ChainedOperationResult.stop() : this.q(var2);
      }
   }

   public ChainedOperationResult q(BasicBlock var1) {
      CodePointer var2 = this.q().q(var1, true, false);
      if (var2 == null) {
         return ChainedOperationResult.stop();
      } else {
         int var3 = ((fA)var1.get(0)).getProcessorMode();
         int var4 = var3 == 64 ? 8 : 4;
         return new ChainedOperationResult(new Pointer(var2.getAddress(), var4, 5));
      }
   }
}
