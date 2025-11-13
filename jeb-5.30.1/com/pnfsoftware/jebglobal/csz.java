package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class csz extends zv {
   private static final ILogger Uv = GlobalLog.getLogger(csz.class);
   private ISegmentInformation oW;
   private long gO;

   public csz(INativeCodeAnalyzer var1, aap.eo var2) {
      super(var1, var2);
      if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb) {
         this.oW = CodeObjectUnitUtil.findSectionByName(var1.getContainer(), ".plt");
         this.gO = ((aae)var1).Dw().io();
      }
   }

   @Override
   protected boolean q(AddressableInstruction var1, BasicBlock var2, long var3) {
      if (this.q == aap.eo.q) {
         return false;
      } else {
         if (this.RF.getProcessor().getType() == ProcessorType.X86) {
            if (this.q(var3, "push ebp", "mov ebp, esp")) {
               return true;
            }

            if (this.q(var3, "push ebp", "push edi", "push esi", "push ebx")) {
               return true;
            }
         }

         if (this.oW != null) {
            long var5 = this.gO + this.oW.getOffsetInMemory();
            long var7 = this.gO + this.oW.getOffsetInMemory() + this.oW.getSizeInMemory();
            if ((var1.getOffset() < var5 || var1.getOffset() > var7) && var3 >= var5 && var3 <= var7) {
               return true;
            }
         }

         return false;
      }
   }
}
