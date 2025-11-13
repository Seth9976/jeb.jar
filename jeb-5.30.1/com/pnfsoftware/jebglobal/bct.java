package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandSized;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bct extends GenericCodeFormatter {
   public bct() {
      this.getDefaultNumberFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.getDefaultAddressFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.setDoNotAttemptImmediateToAddressResolution(true);
   }

   @Override
   public String getRegisterName(long var1) {
      return bdd.q(var1);
   }

   public String q(bdc var1, IInstructionOperandSized var2) {
      return "";
   }

   @Override
   public String getMemoryAccessPrefix() {
      return "";
   }

   @Override
   public String getMemoryAccessSuffix() {
      return "";
   }

   public void q(long var1, bdc var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      bdd var8 = (bdd)var4;
      if (var8.getOperandType() == 4098) {
         int var9 = (int)var8.getOperandValue();
         var7.append(Strings.ff("0x%X", var9));
      } else if (var8.getOperandType() == 4097) {
         int var13 = (int)var8.getOperandValue();
         int var10 = var13 & 0xFF;
         int var11 = var13 >> 8 & 0xFF;

         var7.append(switch (var10) {
            case 1 -> "X";
            case 2 -> "X+";
            case 3 -> "-X";
            case 4 -> "Y";
            case 5 -> "Y+";
            case 6 -> "-Y";
            case 7 -> "Z";
            case 8 -> "Z+";
            case 9 -> "-Z";
            case 10 -> "Y+" + this.getDefaultNumberFormatter().format(8, var11);
            case 11 -> "Z+" + this.getDefaultNumberFormatter().format(8, var11);
            default -> throw new RuntimeException();
         });
      } else {
         super.formatOperand(var1, var3, var4, var5, var6, var7);
      }
   }
}
