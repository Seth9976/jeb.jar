package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.EU;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.iL;

public class wY extends EU {
   private static final ILogger UT = GlobalLog.getLogger(wY.class);
   private ISegmentInformation E;
   private long sY;

   public wY(INativeCodeAnalyzer var1, iL.Av var2) {
      super(var1, var2);
      if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.elf.sy) {
         this.E = CodeObjectUnitUtil.findSectionByName(var1.getContainer(), ".plt");
         this.sY = ((a)var1).wS().NS();
      }
   }

   @Override
   protected boolean pC(AddressableInstruction var1, BasicBlock var2, long var3) {
      if (this.pC == iL.Av.pC) {
         return false;
      } else {
         if (this.A.getProcessor().getType() == ProcessorType.X86) {
            if (this.pC(var3, "push ebp", "mov ebp, esp")) {
               return true;
            }

            if (this.pC(var3, "push ebp", "push edi", "push esi", "push ebx")) {
               return true;
            }
         }

         if (this.E != null) {
            long var5 = this.sY + this.E.getOffsetInMemory();
            long var7 = this.sY + this.E.getOffsetInMemory() + this.E.getSizeInMemory();
            if ((var1.getOffset() < var5 || var1.getOffset() > var7) && var3 >= var5 && var3 <= var7) {
               return true;
            }
         }

         return false;
      }
   }
}
