package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.CoffDebugDirectoryEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public class aco extends IL {
   public aco() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q(ICompiler.COMP_VISUAL_STUDIO).q("winpe").q(CodeGapAnalysisStyle.LINEAR_SWEEP).q());
   }

   @Override
   public aap.eo RF(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      if (!(var2 instanceof com.pnfsoftware.jeb.corei.parsers.winpe.vn)) {
         return null;
      } else if (((com.pnfsoftware.jeb.corei.parsers.winpe.vn)var2).getPEOptionalHeader() != null
         && ((com.pnfsoftware.jeb.corei.parsers.winpe.vn)var2).getPEOptionalHeader().getMajorLinkerVersion() >= 14) {
         List var3 = ((com.pnfsoftware.jeb.corei.parsers.winpe.vn)var2).xK();
         long var4 = var1.getVirtualImageBase();
         if (var3 != null) {
            for (CoffDebugDirectoryEntry var7 : var3) {
               if (var7.getType() == 13) {
                  try {
                     int var8 = var1.getMemory().readInt((var7.getAddressOfRawData() & 4294967295L) + var4);
                     if (var8 != 0 && var8 != 1280590663) {
                        return aap.eo.RF;
                     }

                     return aap.eo.xK;
                  } catch (MemoryException var9) {
                     return null;
                  }
               }
            }
         }

         return aap.eo.RF;
      } else {
         return aap.eo.RF;
      }
   }
}
