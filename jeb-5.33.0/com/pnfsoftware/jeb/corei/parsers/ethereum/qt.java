package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class qt extends GenericCodeFormatter {
   public qt() {
      this.getDefaultNumberFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.setMnemonicRightPaddingLength(0);
   }

   @Override
   public String generateHeader() {
      yt var1 = (yt)this.getCodeUnit().getParent();
      String var2 = S.L("Contract Disassembly");
      if (var1.E.eP != null) {
         var2 = var2 + " - " + Strings.ff(S.L("compiled with solc %s (metadata)"), var1.E.eP);
      }

      return var2;
   }

   @Override
   public String generateExtraMethodComment(long var1) {
      yt var3 = (yt)this.getCodeUnit().getParent();
      eW var4 = var3.kS(var1);
      if (var4 == null) {
         return null;
      } else {
         StringBuilder var5 = new StringBuilder();
         Strings.ff(var5, S.L("EVM routine info: %s"), var4.toString());
         return var5.toString();
      }
   }

   public void pC(long var1, Mh var3, CodeDocumentPart var4) {
      super.formatInstruction(var1, var3, var4);
   }

   public void pC(long var1, Mh var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      yt var8 = (yt)this.getCodeUnit().getParent();
      Tb var9 = (Tb)var4;
      Mh var10 = var8.A(var9.pC.intValue());
      if (var10 != null && var10.pC() == 91) {
         this.formatAddress(var10.UT, var7);
      } else {
         NumberFormatter var11 = this.getNumberFormatter(var9, false);
         if (var11 == null) {
            var11 = this.getDefaultNumberFormatter();
         }

         String var12 = var11.format(256, var9.pC);
         var7.appendAndRecord(var12, ItemClassIdentifiers.IMMEDIATE, this.createItemIdForImmediate(var1, var5));
      }
   }
}
