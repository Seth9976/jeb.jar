package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandSized;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class nA extends GenericCodeFormatter {
   public String pC(vh var1, IInstructionOperandSized var2) {
      int var3 = var2.getOperandBitsize();
      switch (var3) {
         case 0:
            return "";
         case 8:
            return "byte ptr ";
         case 16:
            return "word ptr ";
         case 32:
            return "dword ptr ";
         case 48:
            return "fword ptr ";
         case 64:
            return "qword ptr ";
         case 80:
            return "tword ptr ";
         case 128:
            return "dqword ptr ";
         case 256:
            return "qqword ptr ";
         case 512:
            return "dqqword ptr ";
         default:
            return var3 % 8 == 0 ? Strings.ff("m%d ptr ", var3 / 8) : Strings.ff("%db ptr ", var3);
      }
   }

   public String pC(vh var1, IInstructionOperand var2) {
      Av var3 = (Av)var2;
      long var4 = MG.pC(var1, var3);
      return var4 == 0L ? "" : this.getRegisterName(var4) + ":";
   }

   @Override
   public String getRegisterName(long var1) {
      try {
         return MG.UT(var1);
      } catch (RuntimeException var3) {
         return "unkreg_" + var1;
      }
   }

   protected String pC(long var1, vh var3) {
      if (var3.pC() == 155) {
         INativeContinuousItem var4 = this.getCodeUnit().getNativeItemAt(var1 + 1L);
         if (var4 instanceof INativeInstructionItem) {
            IInstruction var5 = ((INativeInstructionItem)var4).getInstruction();
            if (var5 instanceof vh && (((vh)var5).gp.kS & 131072L) != 0L) {
               return "fwait";
            }
         }
      }

      return super.generateMnemonic(var1, var3);
   }

   public void pC(long var1, vh var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      if (var4 instanceof Av var8) {
         switch (var8.getOperandType()) {
            case 8:
               this.pC(var1, var3, (QM)var4, var5, var7);
               return;
            case 4097:
               this.pC(var1, var3, (lB)var4, var5, var7);
               return;
         }
      }

      super.formatOperand(var1, var3, var4, var5, var6, var7);
   }

   private void pC(long var1, vh var3, lB var4, int var5, CodeDocumentPart var6) {
      int var7 = var4.kS();
      long var8 = var4.wS() & 4294967295L;
      String var10 = Strings.ff("%Xh:%Xh", var7, var8);
      var6.append(var10);
   }

   private void pC(long var1, vh var3, QM var4, int var5, CodeDocumentPart var6) {
      var6.append(this.pC(var3, (IInstructionOperandSized)var4));
      var6.append(this.pC(var3, (IInstructionOperand)var4));
      var6.append(this.getMemoryAccessPrefix());
      long var7 = var4.getMemoryBaseRegister();
      long var9 = var4.getMemoryIndexRegister();
      long var11 = var4.getMemoryDisplacement();
      if (var1 != 0L && var9 < 0L && var7 >= 0L && MG.A(var7) == 10) {
         long var17 = var11;
         int var15 = MG.kS(var7);
         if (var15 == 32) {
            var17 = var11 + var1 + var3.getSize() & 4294967295L;
         } else if (var15 == 64) {
            var17 = var11 + var1 + var3.getSize();
         }

         this.formatAddress(var17, var6);
      } else {
         if (var7 >= 0L) {
            this.formatRegister(var7, var6);
         }

         if (var9 >= 0L) {
            if (var7 >= 0L) {
               var6.append("+");
            }

            int var13 = var4.getMemoryScale();
            if (var13 >= 2) {
               var6.append(var13 + "*");
            }

            this.formatRegister(var9, var6);
         }

         if (var11 != 0L) {
            if (var7 < 0L && var9 < 0L) {
               var11 &= MathUtil.makeMask(this.getCodeUnit().getCodeAnalyzer().getProcessor().getMode());
            } else if (var11 < 0L) {
               var11 = -var11;
               var6.append("-");
            } else {
               var6.append("+");
            }

            if (this.getCodeUnit().getCodeAnalyzer().getAnalysisRanges().contains(var11) && this.getCodeUnit().getNativeItemAt(var11) != null) {
               this.formatAddress(var11, var6);
            } else {
               String var16 = this.getDefaultNumberFormatter().format(64, var11, NumberFormatter.NumberBase.HEXADECIMAL, false);
               var6.appendAndRecord(var16, ItemClassIdentifiers.IMMEDIATE);
            }
         } else if (var7 < 0L && var9 < 0L) {
            var6.appendAndRecord("0", ItemClassIdentifiers.IMMEDIATE);
         }
      }

      var6.append(this.getMemoryAccessSuffix());
   }
}
