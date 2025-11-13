package com.pnfsoftware.jeb.corei.parsers.arm;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Wl;
import com.pnfsoftware.jebglobal.Yg;

@Ser
public class Sv extends GenericCodeFormatter {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);

   public Sv() {
      this.getDefaultNumberFormatter().setSignedNumber(true);
      this.getDefaultNumberFormatter().setForcePositiveRenderingForNonBase10(true);
   }

   protected void pC(long var1, rQ var3, boolean var4, CodeDocumentPart var5) {
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

               String var8 = var6 == 1 ? var3.wS().pC(true) : var3.wS().sY();
               var5.appendAndRecord(var8, ItemClassIdentifiers.MNEMONIC, this.createItemIdForMnemonic(var3));
               var7 += var8.length();
               if (var3.A() != null && var3.A().length != 0) {
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
         return LC.kS(var1, this.getCodeUnit().getProcessor().getMode());
      } catch (RuntimeException var4) {
         pC.catchingSilent(var4);
         return "unkreg_" + var1;
      }
   }

   public void pC(long var1, rQ var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      if (var4 instanceof Yg var8) {
         switch (var8.getOperandType()) {
            case 4097:
               this.pC(var1, var3, (Wl)var4, var7);
               return;
         }
      }

      super.formatOperand(var1, var3, var4, var5, var6, var7);
   }

   private void pC(long var1, rQ var3, Wl var4, CodeDocumentPart var5) {
      this.addPrefix(var3, var4, var5);
      var5.append(var4.pC(var3, var1));
      this.addSuffix(var3, var4, var5);
   }

   @Override
   public NumberFormatter getNumberFormatter(IInstructionOperand var1, boolean var2) {
      if (var1 instanceof Yg && (((Yg)var1).getFlags() & 524288) != 0) {
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
