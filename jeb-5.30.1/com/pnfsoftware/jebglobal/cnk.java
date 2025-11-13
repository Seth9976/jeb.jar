package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cnk extends GenericCodeFormatter {
   @Override
   public String getRegisterName(long var1) {
      boolean var3 = false;
      if (this.getCodeUnit().getPropertyManager().getBoolean("DisplayAbiRegisterNames")) {
         var3 = true;
      }

      return ((cno)this.getCodeUnit().getProcessor()).RF(var1, var3);
   }

   public void q(long var1, cnl var3, CodeDocumentPart var4) {
      if (var3.getMnemonic().equals("jr")
         && ((cnm)var3.getOperand(0)).getOperandValue() == 1L
         && this.getCodeUnit().getPropertyManager().getBoolean("RenderJRX1AsRet")) {
         var4.appendAndRecord("ret", ItemClassIdentifiers.MNEMONIC, 0L);
      } else {
         super.formatInstruction(var1, var3, var4);
      }
   }
}
