package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class by extends EU {
   private static final ILogger UT = GlobalLog.getLogger(by.class);
   private Nd E;
   private ISegmentInformation sY;
   private long ys;

   public by(INativeCodeAnalyzer var1, Nd var2, iL.Av var3) {
      super(var1, var3);
      this.E = var2;
      if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.elf.sy) {
         this.sY = CodeObjectUnitUtil.findSectionByName(var1.getContainer(), ".plt");
         this.ys = ((a)var1).wS().NS();
      }
   }

   @Override
   protected boolean pC(AddressableInstruction var1, BasicBlock var2, long var3) {
      if (this.pC == iL.Av.pC) {
         return false;
      } else {
         INativeContinuousItem var5 = this.kS.getPreviousItem(var1.getOffset());
         if (var5 instanceof INativeInstructionItem) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var5).getInstruction();
            if (PU.kS(var6, true)) {
               return true;
            }
         }

         INativeContinuousItem var13 = this.kS.getItemAt(var3);
         if (var13 instanceof INativeInstructionItem) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var13).getInstruction();
            if (PU.pC(var7, false)) {
               return true;
            }

            if (this.pC(var7)) {
               INativeContinuousItem var8 = this.kS.getNextItem(var3);
               if (var8 instanceof INativeInstructionItem) {
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var9 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var8).getInstruction();
                  if (PU.pC(var9, false)) {
                     return true;
                  }
               }
            }
         }

         if (this.sY != null) {
            long var14 = this.ys + this.sY.getOffsetInMemory();
            long var15 = this.ys + this.sY.getOffsetInMemory() + this.sY.getSizeInMemory();
            if ((var1.getOffset() < var14 || var1.getOffset() > var15) && var3 >= var14 && var3 <= var15) {
               return true;
            }
         }

         if (var1.getProcessorMode() == 32 && this.E.pC().pC(var2, false) != null) {
            return true;
         } else {
            if (var1.getProcessorMode() == 16) {
               try {
                  if (this.A.getMemory().readInt(var3) == 1187006328) {
                     return true;
                  }
               } catch (MemoryException var12) {
                  return false;
               }
            }

            if (var1.getProcessorMode() != 64) {
               return false;
            } else if (this.E.pC().wS(var2) != null) {
               return true;
            } else {
               try {
                  return (this.A.getMemory().readInt(var3) & 2118124512) == 671089632 ? true : (this.A.getMemory().readInt(var3) & 1593836543) == 1358955519;
               } catch (MemoryException var11) {
                  return false;
               }
            }
         }
      }
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      if (var1.wS().pC().equalsIgnoreCase("LDR")) {
         Yg var2 = var1.A()[0];
         if (var2 instanceof LC && !((LC)var2).A(var1.getProcessorMode()) && !((LC)var2).kS(var1.getProcessorMode())) {
            Yg var3 = var1.A()[1];
            if (var3 instanceof KH && ((KH)var3).A().A(var1.getProcessorMode())) {
               return true;
            }
         }
      }

      return false;
   }
}
