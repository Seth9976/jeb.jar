package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class FE extends GenericCodeFormatter {
   private static final ILogger q = GlobalLog.getLogger(FE.class);

   public FE() {
      this.getDefaultNumberFormatter().setSignedNumber(true);
      this.getDefaultNumberFormatter().setForcePositiveRenderingForNonBase10(true);
   }

   protected void q(long var1, fA var3, boolean var4, CodeDocumentPart var5) {
      if (var3.getProcessorMode() == 16 && var3.getSize() != 2) {
         int var6 = this.getCodeUnit().getPropertyManager().getInteger(UnitUtil.unitProperty(this.getCodeUnit(), "ForceWideFlag"));
         switch (var6) {
            case 2:
               super.formatMnemonic(var1, var3, var4, var5);
               return;
            default:
               int var7 = 0;
               if (var4) {
                  var7 = this.formatPrefix(var1, var3, var5);
               }

               String var8 = var6 == 1 ? var3.Dw().q(true) : var3.Dw().nf();
               var5.appendAndRecord(var8, ItemClassIdentifiers.MNEMONIC, this.createItemIdForMnemonic(var3));
               var7 += var8.length();
               if (var3.RF() != null && var3.RF().length != 0) {
                  int var9 = Math.max(1, this.getMnemonicRightPaddingLength() - var7);
                  var5.space(var9);
               }
         }
      } else {
         super.formatMnemonic(var1, var3, var4, var5);
      }
   }

   @Override
   public String getRegisterName(long var1) {
      try {
         return GC.xK(var1, this.getCodeUnit().getProcessor().getMode());
      } catch (RuntimeException var4) {
         q.catchingSilent(var4);
         return "unkreg_" + var1;
      }
   }

   public void q(long var1, fA var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      if (var4 instanceof CW var8) {
         switch (var8.getOperandType()) {
            case 4097:
               this.q(var1, var3, (HR)var4, var7);
               return;
         }
      }

      super.formatOperand(var1, var3, var4, var5, var6, var7);
   }

   private void q(long var1, fA var3, HR var4, CodeDocumentPart var5) {
      this.addPrefix(var3, var4, var5);
      var5.append(var4.q(var3, var1));
      this.addSuffix(var3, var4, var5);
   }

   @Override
   public NumberFormatter getNumberFormatter(IInstructionOperand var1, boolean var2) {
      if (var1 instanceof CW && (((CW)var1).getFlags() & 524288) != 0) {
         NumberFormatter var4 = super.getNumberFormatter(var1, false);
         if (var4 == null) {
            var4 = super.getNumberFormatter(var1, true);
            var4.setBase(NumberFormatter.NumberBase.DECIMAL);
         }

         return var4;
      } else {
         NumberFormatter var3 = super.getNumberFormatter(var1, var2);
         return var3 == null ? this.getDefaultNumberFormatter() : var3;
      }
   }
}
