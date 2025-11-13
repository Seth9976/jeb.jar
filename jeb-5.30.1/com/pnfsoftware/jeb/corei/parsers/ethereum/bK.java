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
public class bK extends GenericCodeFormatter {
   public bK() {
      this.getDefaultNumberFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.setMnemonicRightPaddingLength(0);
   }

   @Override
   public String generateHeader() {
      PY var1 = (PY)this.getCodeUnit().getParent();
      String var2 = S.L("Contract Disassembly");
      if (var1.gO.PV != null) {
         var2 = var2 + " - " + Strings.ff(S.L("compiled with solc %s (metadata)"), var1.gO.PV);
      }

      return var2;
   }

   @Override
   public String generateExtraMethodComment(long var1) {
      PY var3 = (PY)this.getCodeUnit().getParent();
      qx var4 = var3.xK(var1);
      if (var4 == null) {
         return null;
      } else {
         StringBuilder var5 = new StringBuilder();
         Strings.ff(var5, S.L("EVM routine info: %s"), var4.toString());
         return var5.toString();
      }
   }

   public void q(long var1, vX var3, CodeDocumentPart var4) {
      super.formatInstruction(var1, var3, var4);
   }

   public void q(long var1, vX var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      PY var8 = (PY)this.getCodeUnit().getParent();
      KZ var9 = (KZ)var4;
      vX var10 = var8.RF(var9.q.intValue());
      if (var10 != null && var10.q() == 91) {
         this.formatAddress(var10.zz, var7);
      } else {
         NumberFormatter var11 = this.getNumberFormatter(var9, false);
         if (var11 == null) {
            var11 = this.getDefaultNumberFormatter();
         }

         String var12 = var11.format(256, var9.q);
         var7.appendAndRecord(var12, ItemClassIdentifiers.IMMEDIATE, this.createItemIdForImmediate(var1, var5));
      }
   }
}
