package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class zJ extends GenericCodeFormatter {
   public zJ() {
      this.setMnemonicRightPaddingLength(0);
   }

   @Override
   public String generateExtraMethodComment(long var1) {
      qx var3 = (qx)this.getCodeUnit().getParent();
      Nt var4 = var3.q(var1);
      if (var4 == null) {
         return null;
      } else {
         StringBuilder var5 = new StringBuilder(S.L("Wasm signature"));
         Strings.ff(var5, ": %s / ", var4.q.toString());
         int var6 = var4.q.RF.length;
         Strings.ff(var5, S.L("Locals: %d (including %d parameters)"), var4.RF.q.size(), var6);
         return var5.toString();
      }
   }

   public void q(long var1, SG var3, CodeDocumentPart var4) {
      String var5 = Strings.ff("%d [%d]%s", var3.oW, var3.nf, Strings.spaces(var3.oW * 2));
      var4.appendAndRecord(var5, ItemClassIdentifiers.COMMENT);
      var4.space(2);
      super.formatInstruction(var1, var3, var4);
   }

   public void q(long var1, SG var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      qx var8 = (qx)this.getCodeUnit().getParent();
      ct var9 = (ct)var4;
      var9.RF();
      int var10 = var9.xK();
      Object var11 = var9.Dw();
      if (var10 == 1) {
         long var12 = (Long)var11;
         NumberFormatter var14 = this.getNumberFormatter(var9, false);
         if (var14 == null) {
            var14 = this.getDefaultNumberFormatter();
         }

         String var15 = var14.format(64, var12);
         var7.appendAndRecord(var15, ItemClassIdentifiers.IMMEDIATE, this.createItemIdForImmediate(var1, var5));
      } else if (var10 == 3) {
         int var16 = ((Long)var11).intValue();
         Nt var13 = var8.RF(var16);
         ItemClassIdentifiers var22 = var13.nf() ? ItemClassIdentifiers.EXTERNAL_METHOD_NAME : ItemClassIdentifiers.METHOD_NAME;
         var7.appendAndRecord(var13.zz(), var22);
      } else if (var10 == 4) {
         int var17 = ((Long)var11).intValue();
         iZ var20 = var8.oW(var17);
         var7.appendAndRecord(var20.toString(), ItemClassIdentifiers.TYPE_PROTOTYPE);
      } else if (var10 == 5) {
         int var18 = ((Long)var11).intValue();
         var7.appendAndRecord("$L" + var18, ItemClassIdentifiers.IDENTIFIER, this.createItemIdForRegister(var18));
      } else if (var10 == 6) {
         int var19 = ((Long)var11).intValue();
         tw var21 = var8.xK(var19);
         var7.appendAndRecord("$" + var21.zz(), ItemClassIdentifiers.IDENTIFIER, this.createItemIdForAddress(var21.xK));
      } else {
         var7.append(var9.format(var3, var1));
      }
   }
}
