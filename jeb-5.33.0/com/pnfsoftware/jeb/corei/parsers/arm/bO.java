package com.pnfsoftware.jeb.corei.parsers.arm;

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
import com.pnfsoftware.jebglobal.HL;

@Ser
public class bO extends AbstractAnalyzerExtension {
   private static final ILogger pC = GlobalLog.getLogger(bO.class);
   @SerTransient
   private HL A;

   public HL pC() {
      if (this.A == null) {
         this.A = new HL(this.gca);
      }

      return this.A;
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      if (var1.size() > 2 && ((rQ)var1.get(0).get(0)).getProcessorMode() == 64) {
         return ChainedOperationResult.stop();
      } else {
         BasicBlock var2 = var1.getEntryBlock();
         return var2.isEmpty() ? ChainedOperationResult.stop() : this.pC(var2);
      }
   }

   public ChainedOperationResult pC(BasicBlock var1) {
      CodePointer var2 = this.pC().pC(var1, true, false);
      if (var2 == null) {
         return ChainedOperationResult.stop();
      } else {
         int var3 = ((rQ)var1.get(0)).getProcessorMode();
         int var4 = var3 == 64 ? 8 : 4;
         return new ChainedOperationResult(new Pointer(var2.getAddress(), var4, 5));
      }
   }
}
