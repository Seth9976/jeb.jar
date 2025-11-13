package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class vn extends GenericCodeFormatter {
   private LR q() {
      ICodeObjectUnit var1 = this.getCodeUnit().getCodeObjectContainer();
      return var1 instanceof LR ? (LR)var1 : null;
   }

   @Override
   public String generateHeader() {
      String var1 = Strings.ff(
         S.L("S7 PLC Blocks Disassembly\nRefer to the Manual to get started on the analysis of S7 PLC code and data (%s)\n"), "jeb.io/rev-s7"
      );
      LR var2 = this.q();
      if (var2 != null && !var2.q()) {
         var1 = var1 + Strings.ff(S.L("Tip: To process multiple blocks, pack them in a zip archive having the extension '%s'\n"), ".s7zip");
      }

      return var1;
   }

   @Override
   public String generateExtraSegmentHeader(ISegmentInformation var1) {
      LR var2 = this.q();
      if (var2 != null) {
         IS7Block var3 = var2.getBlockAt(var2.getLoaderInformation().getImageBase() + var1.getOffsetInMemory());
         if (var3 != null) {
            StringBuilder var4 = new StringBuilder(S.L("Segment maps block"));
            var4.append(" ");
            Strings.ff(
               var4,
               S.L("%s%d, version %d.%d, programmed in %s"),
               S7.BlockType.fromId(var3.getTypeId()),
               var3.getNumber(),
               var3.getVersion()[0],
               var3.getVersion()[1],
               S7.LangType.fromId(var3.getSourceLanguageId())
            );
            Strings.ff(
               var4,
               S.L("\nModified on %s, interface changed on %s"),
               TimeFormatter.formatTimestampLocal(var3.getModificationTimestamp()),
               TimeFormatter.formatTimestampLocal(var3.getInterfaceModificationTimestamp())
            );
            Strings.ff(
               var4,
               S.L("\nName: %s, Family: %s, Author: %s"),
               Strings.safe2(var3.getMetadataBlockName(), "<UNKNOWN>"),
               Strings.safe2(var3.getMetadataFamilyName(), "<UNKNOWN>"),
               Strings.safe2(var3.getMetadataAuthorName(), "<UNKNOWN>")
            );
            Strings.ff(var4, S.L("\nKey: 0x%X, CRC: 0x%X."), var3.getKey(), var3.getCrc());
            String var5 = var2.getBlockEntryName(var3);
            if (var5 != null) {
               Strings.ff(var4, S.L("\nEntry name in archive: %s"), var5);
            }

            return var4.toString();
         }
      }

      return super.generateExtraSegmentHeader(var1);
   }

   public void q(long var1, PY var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      LR var8 = this.q();
      if (var8 != null && var8.getPropertyManager().getBoolean("AugmentDisassembly")) {
         vb var9 = (vb)var4;
         if (var9.q() != 1792 && var9.q() == 768) {
            long var10 = var9.q(var1);
            String var12 = this.getCodeUnit().getCodeModel().getLabelManager().getLabel(var10, 0L, AutoLabelPolicy.ON);
            var7.append(var12);
            return;
         }
      }

      super.formatOperand(var1, var3, var4, var5, var6, var7);
   }
}
