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

public class o extends zv {
   private static final ILogger Uv = GlobalLog.getLogger(o.class);
   private Jc oW;
   private ISegmentInformation gO;
   private long nf;

   public o(INativeCodeAnalyzer var1, Jc var2, aap.eo var3) {
      super(var1, var3);
      this.oW = var2;
      if (var1.getContainer() instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb) {
         this.gO = CodeObjectUnitUtil.findSectionByName(var1.getContainer(), ".plt");
         this.nf = ((aae)var1).Dw().io();
      }
   }

   @Override
   protected boolean q(AddressableInstruction var1, BasicBlock var2, long var3) {
      if (this.q == aap.eo.q) {
         return false;
      } else {
         INativeContinuousItem var5 = this.xK.getPreviousItem(var1.getOffset());
         if (var5 instanceof INativeInstructionItem) {
            fA var6 = (fA)((INativeInstructionItem)var5).getInstruction();
            if (OC.xK(var6, true)) {
               return true;
            }
         }

         INativeContinuousItem var13 = this.xK.getItemAt(var3);
         if (var13 instanceof INativeInstructionItem) {
            fA var7 = (fA)((INativeInstructionItem)var13).getInstruction();
            if (OC.q(var7, false)) {
               return true;
            }

            if (this.q(var7)) {
               INativeContinuousItem var8 = this.xK.getNextItem(var3);
               if (var8 instanceof INativeInstructionItem) {
                  fA var9 = (fA)((INativeInstructionItem)var8).getInstruction();
                  if (OC.q(var9, false)) {
                     return true;
                  }
               }
            }
         }

         if (this.gO != null) {
            long var14 = this.nf + this.gO.getOffsetInMemory();
            long var15 = this.nf + this.gO.getOffsetInMemory() + this.gO.getSizeInMemory();
            if ((var1.getOffset() < var14 || var1.getOffset() > var15) && var3 >= var14 && var3 <= var15) {
               return true;
            }
         }

         if (var1.getProcessorMode() == 32 && this.oW.RF().q(var2, false) != null) {
            return true;
         } else {
            if (var1.getProcessorMode() == 16) {
               try {
                  if (this.RF.getMemory().readInt(var3) == 1187006328) {
                     return true;
                  }
               } catch (MemoryException var12) {
                  return false;
               }
            }

            if (var1.getProcessorMode() != 64) {
               return false;
            } else if (this.oW.RF().Dw(var2) != null) {
               return true;
            } else {
               try {
                  return (this.RF.getMemory().readInt(var3) & 2118124512) == 671089632 ? true : (this.RF.getMemory().readInt(var3) & 1593836543) == 1358955519;
               } catch (MemoryException var11) {
                  return false;
               }
            }
         }
      }
   }

   private boolean q(fA var1) {
      if (var1.Dw().q().equalsIgnoreCase("LDR")) {
         CW var2 = var1.RF()[0];
         if (var2 instanceof GC && !((GC)var2).RF(var1.getProcessorMode()) && !((GC)var2).xK(var1.getProcessorMode())) {
            CW var3 = var1.RF()[1];
            if (var3 instanceof wh && ((wh)var3).RF().RF(var1.getProcessorMode())) {
               return true;
            }
         }
      }

      return false;
   }
}
