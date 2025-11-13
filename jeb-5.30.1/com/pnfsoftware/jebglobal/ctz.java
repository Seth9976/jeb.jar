package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class ctz extends zv {
   private static final ILogger Uv = GlobalLog.getLogger(ctz.class);

   public ctz(INativeCodeAnalyzer var1, aap.eo var2) {
      super(var1, var2);
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

            if (this.q(var3, "mov edi, edi", "push ebp", "mov ebp, esp")) {
               return true;
            }
         }

         if (this.q == aap.eo.xK) {
            long var5 = Math.min(var3, var1.getOffset() + var1.getSize());
            long var7 = Math.max(var3, var1.getOffset());
            long var9 = var5;

            while (var9 < var7 && var9 - var5 <= 65536L) {
               INativeContinuousItem var11 = this.RF.za().getItemOver(var9);
               if (var11 == null) {
                  try {
                     if (this.RF.getMemory().readByte(var9) == -52) {
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
