package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cjm extends EU {
   private static final ILogger UT = GlobalLog.getLogger(cjm.class);

   public cjm(INativeCodeAnalyzer var1, iL.Av var2) {
      super(var1, var2);
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

            if (this.pC(var3, "mov edi, edi", "push ebp", "mov ebp, esp")) {
               return true;
            }
         }

         if (this.pC == iL.Av.kS) {
            long var5 = Math.min(var3, var1.getOffset() + var1.getSize());
            long var7 = Math.max(var3, var1.getOffset());
            long var9 = var5;

            while (var9 < var7 && var9 - var5 <= 65536L) {
               INativeContinuousItem var11 = this.A.ys().getItemOver(var9);
               if (var11 == null) {
                  try {
                     if (this.A.getMemory().readByte(var9) == -52) {
                        return true;
                     }
                  } catch (MemoryException var12) {
                     return false;
                  }

                  var9++;
               } else {
                  var9 += var11.getMemorySize();
               }
            }
         }

         return false;
      }
   }
}
