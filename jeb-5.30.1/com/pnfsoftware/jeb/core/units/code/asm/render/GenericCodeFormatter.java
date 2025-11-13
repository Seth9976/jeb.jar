package com.pnfsoftware.jeb.core.units.code.asm.render;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandSized;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.axh;
import com.pnfsoftware.jebglobal.axu;

@Ser
public class GenericCodeFormatter {
   public static final int MNEMONIC_RIGHT_PADDING_LENGTH_DEFAULT = 10;
   @SerId(1)
   private abg pbcu;
   @SerId(2)
   private IVirtualMemory mem;
   @SerId(3)
   private Endianness endianness;
   @SerId(4)
   private NumberFormatter defaultNumberFormatter = new NumberFormatter();
   @SerId(5)
   private int mnemonicRightPaddingLength = 10;
   @SerId(6)
   private WeakIdentityHashMap operandNumberFormatterMap = new WeakIdentityHashMap();
   @SerId(7)
   private AddressFormatter defaultAddressFormatter = new AddressFormatter();
   @SerId(8)
   private boolean doNotResolveImmAsAddr;

   public GenericCodeFormatter() {
   }

   public GenericCodeFormatter(IVirtualMemory var1) {
      this.mem = var1;
      this.endianness = var1.getStandardEndianess();
      this.defaultNumberFormatter.setEndianness(this.endianness);
   }

   public void setCodeUnit(INativeCodeUnit var1) {
      this.pbcu = (abg)var1;
      this.mem = var1.getMemory();
      this.endianness = var1.getEndianness();
      this.defaultNumberFormatter.setEndianness(this.endianness);
   }

   public INativeCodeUnit getCodeUnit() {
      return this.pbcu;
   }

   public IVirtualMemory getMemory() {
      return this.mem;
   }

   public Endianness getEndianness() {
      return this.endianness;
   }

   public String getRawDataDeclarator(int var1) {
      switch (var1) {
         case 8:
            return "db";
         case 16:
            return "dw";
         case 32:
            return "dd";
         case 64:
            return "dq";
         default:
            Assert.a(var1 > 0);
            return "d" + var1;
      }
   }

   public String getDataSeparator() {
      return ",";
   }

   public int getArrayElementPerLine() {
      return 10;
   }

   public void formatDataDeclarator(int var1, CodeDocumentPart var2) {
      String var3 = this.getRawDataDeclarator(var1 * 8);
      var2.appendAndRecord(var3, ItemClassIdentifiers.KEYWORD);
      var2.space();
   }

   public String getProcedureDefinitionStart() {
      return "proc";
   }

   public String getProcedureDefinitionEnd() {
      return "endp";
   }

   public String getLabelPrefix() {
      return "";
   }

   public String getLabelSuffix() {
      return ":";
   }

   public String getOperandSeparator() {
      return ",";
   }

   public String getMemoryAccessSizeInfo(IInstruction var1, IInstructionOperandSized var2) {
      return Strings.ff("ptr%d", var2.getOperandBitsize());
   }

   public String getMemoryAccessPrefix() {
      return "[";
   }

   public String getMemoryAccessSuffix() {
      return "]";
   }

   public String getMemoryAccessSegmentInfo(IInstruction var1, IInstructionOperand var2) {
      return "";
   }

   public String getInlineCommentString() {
      return ";";
   }

   public String getMultiLineCommentBegin() {
      return "/*";
   }

   public String getMultiLineCommentEnd() {
      return "*/";
   }

   public String getRegisterName(long var1) {
      return "r" + var1;
   }

   public String generateHeader() {
      return " === " + S.L("Code Disassembly") + " ===";
   }

   public String generateExtraSegmentHeader(ISegmentInformation var1) {
      return null;
   }

   public String generateExtraSectionHeader(ISegmentInformation var1) {
      return null;
   }

   public String generateExtraMethodComment(long var1) {
      return null;
   }

   public CharSequence generateExtraComment(long var1, IInstruction var3) {
      return null;
   }

   public void formatInstruction(long var1, IInstruction var3, CodeDocumentPart var4) {
      this.formatMnemonic(var1, var3, true, var4);
      this.formatOperands(var1, var3, var4);
   }

   protected int formatPrefix(long var1, IInstruction var3, CodeDocumentPart var4) {
      String var5 = var3.getPrefix();
      if (var5 == null) {
         return 0;
      } else {
         var4.appendAndRecord(var5, ItemClassIdentifiers.MNEMONIC_PREFIX);
         var4.space();
         return var5.length() + 1;
      }
   }

   protected void formatMnemonic(long var1, IInstruction var3, boolean var4, CodeDocumentPart var5) {
      int var6 = 0;
      if (var4) {
         var6 = this.formatPrefix(var1, var3, var5);
      }

      String var7 = this.generateMnemonic(var1, var3);
      var5.appendAndRecord(var7, ItemClassIdentifiers.MNEMONIC, this.createItemIdForMnemonic(var3));
      var6 += var7.length();
      if (var3.getOperands() != null && var3.getOperands().length != 0) {
         int var8 = Math.max(1, this.mnemonicRightPaddingLength - var6);
         var5.space(var8);
      }
   }

   protected String generateMnemonic(long var1, IInstruction var3) {
      return var3.getMnemonic();
   }

   public final void setMnemonicRightPaddingLength(int var1) {
      this.mnemonicRightPaddingLength = var1;
   }

   public final int getMnemonicRightPaddingLength() {
      return this.mnemonicRightPaddingLength;
   }

   protected void formatOperands(long var1, IInstruction var3, CodeDocumentPart var4) {
      int var5 = 0;

      for (IInstructionOperand var9 : var3.getOperands()) {
         if (var5 >= 1) {
            var4.append(this.getOperandSeparator());
            var4.space();
         }

         this.formatOperand(var1, var3, var9, var5, 0, var4);
         var5++;
      }
   }

   public void formatOperand(long var1, IInstruction var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      boolean var8 = false;
      if (var4 instanceof IInstructionOperandGeneric var9) {
         var8 = this.formatOperandGeneric(var1, var3, var9, var5, var6, var7);
      }

      if (!var8) {
         String var10 = var4.format(var3, var1);
         var7.append(var10);
      }
   }

   protected final void addPrefix(IInstruction var1, IInstructionOperandGeneric var2, CodeDocumentPart var3) {
      String var4 = var2.getPrefix(var1);
      if (var4 != null) {
         var3.append(var4);
      }
   }

   protected final void addSuffix(IInstruction var1, IInstructionOperandGeneric var2, CodeDocumentPart var3) {
      String var4 = var2.getSuffix(var1);
      if (var4 != null) {
         var3.append(var4);
      }
   }

   private boolean formatOperandGeneric(long var1, IInstruction var3, IInstructionOperandGeneric var4, int var5, int var6, CodeDocumentPart var7) {
      int var8 = var4.getOperandType();
      long var9 = var4.getOperandValue(var1);
      switch (var8) {
         case 0:
            this.addPrefix(var3, var4, var7);
            this.formatRegister(var9, var7);
            break;
         case 1:
         case 9:
            this.addPrefix(var3, var4, var7);
            NumberFormatter var16 = this.getNumberFormatter(var4, false);
            if (var16 == null) {
               if (!this.isDoNotAttemptImmediateToAddressResolution()) {
                  String var17 = this.pbcu.q(var9, AutoLabelPolicy.ITEM);
                  if (var17 != null) {
                     var7.append("offset ");
                     this.formatAddress(var9, var7);
                     break;
                  }
               }

               var16 = this.getDefaultNumberFormatter();
            }

            String var18 = var16.format(var4.getOperandBitsize(), var9, var16.getBase(), var8 == 1 ? var16.isSignedNumber() : true);
            var7.appendAndRecord(var18, ItemClassIdentifiers.IMMEDIATE, this.createItemIdForImmediate(var1, var5));
            break;
         case 2:
            this.addPrefix(var3, var4, var7);
            this.formatAddress(var9, var7);
            break;
         case 3:
            this.addPrefix(var3, var4, var7);
            this.formatRelativeAddress(var4, var9, var7);
            break;
         case 4:
         case 5:
            this.addPrefix(var3, var4, var7);
            var7.append(this.getMemoryAccessSizeInfo(var3, var4));
            var7.append(this.getMemoryAccessSegmentInfo(var3, var4));
            var7.append(this.getMemoryAccessPrefix());
            if (var8 == 4) {
               this.formatRegister(var9, var7);
            } else {
               this.formatAddress(var9, var7);
            }

            var7.append(this.getMemoryAccessSuffix());
            break;
         case 6:
            this.addPrefix(var3, var4, var7);
            var7.appendAndRecord(var4.getAlias(var9), ItemClassIdentifiers.KEYWORD);
            break;
         case 7:
            IInstructionOperandList var11 = (IInstructionOperandList)var4;
            IInstructionOperandGeneric var12 = var11.merge(var1);
            if (var12 != null) {
               this.formatOperand(var1, var3, var12, var5, var6, var7);
               return true;
            }

            this.addPrefix(var3, var4, var7);
            int var13 = var6 + 1;

            for (int var14 = 0; var14 < var11.getOperands().length; var14++) {
               if (var14 != 0) {
                  var7.append(var11.getSeparator());
               }

               int var15 = var5 | var14 << 4 * var13;
               this.formatOperand(var1, var3, var11.getOperands()[var14], var15, var13, var7);
            }
            break;
         case 8:
         default:
            return false;
      }

      this.addSuffix(var3, var4, var7);
      return true;
   }

   public final void formatImm(long var1, IInstructionOperandSized var3, long var4, int var6, CodeDocumentPart var7) {
      NumberFormatter var8 = this.getNumberFormatter(var3, false);
      if (var8 == null) {
         if (!this.isDoNotAttemptImmediateToAddressResolution()) {
            String var9 = this.pbcu.q(var4, AutoLabelPolicy.ITEM);
            if (var9 != null) {
               var7.append("offset ");
               this.formatAddress(var4, var7);
               return;
            }
         }

         var8 = this.getDefaultNumberFormatter();
      }

      String var10 = var8.format(var3.getOperandBitsize(), var4);
      var7.appendAndRecord(var10, ItemClassIdentifiers.IMMEDIATE, this.createItemIdForImmediate(var1, var6));
   }

   public final void formatAddress(long var1, CodeDocumentPart var3) {
      this.formatAddress(var1, null, var3);
   }

   public final void formatRelativeAddress(IInstructionOperandGeneric var1, long var2, CodeDocumentPart var4) {
      this.formatAddress(var2, var1, var4);
   }

   public void formatAddress(long var1, IInstructionOperandGeneric var3, CodeDocumentPart var4) {
      INativeContinuousItem var5 = this.pbcu.getNativeItemOver(var1);
      if (var3 != null
         || var5 == null
         || var5.getMemoryAddress() == var1
         || this.getDefaultAddressFormatter().getBase() != AddressFormatter.AddressBase.ABSOLUTE_ADDRESS
            && this.getDefaultAddressFormatter().getBase() != AddressFormatter.AddressBase.LABEL) {
         long var7;
         String var11;
         if (this.pbcu.Uv(var1)) {
            var11 = this.getDefaultAddressFormatter().format(this.pbcu, this.mem.getSpaceBits(), var1, var3);
            var7 = this.createItemIdForAddress(var1);
         } else {
            var11 = this.getDefaultAddressFormatter().format(this.mem.getSpaceBits(), var1, var3);
            var7 = 0L;
         }

         ItemClassIdentifiers var9 = this.getBestClassIdForAddress(var1);
         var4.appendAndRecord(var11, var9, var7);
      } else {
         long var6 = var1 - var5.getMemoryAddress();
         this.formatAddress(var5.getMemoryAddress(), var3, var4);
         var4.append("+");
         long var8 = 0L;
         if (this.pbcu.Uv(var1)) {
            var8 = this.createItemIdForAddress(var1);
         }

         String var10 = this.getDefaultNumberFormatter().format(32, var6, NumberFormatter.NumberBase.HEXADECIMAL, false);
         var4.appendAndRecord(var10, ItemClassIdentifiers.NUMBER, var8);
      }
   }

   protected void formatRegister(long var1, CodeDocumentPart var3) {
      var3.appendAndRecord(this.getRegisterName(var1), ItemClassIdentifiers.REGISTER, this.createItemIdForDefaultRegister(var1));
   }

   public NumberFormatter getDefaultNumberFormatter() {
      return this.defaultNumberFormatter;
   }

   public NumberFormatter getNumberFormatter(INativeDataItem var1) {
      NumberFormatter var2 = ((axh)var1).q(false);
      if (var2 == null) {
         var2 = this.defaultNumberFormatter;
      } else {
         var2.setEndianness(this.endianness);
      }

      return var2;
   }

   public NumberFormatter getNumberFormatter(IInstructionOperand var1, boolean var2) {
      NumberFormatter var3 = (NumberFormatter)this.operandNumberFormatterMap.get(var1);
      if (var3 == null && var2) {
         var3 = new NumberFormatter(this.defaultNumberFormatter);
         this.operandNumberFormatterMap.put(var1, var3);
      }

      return var3;
   }

   public AddressFormatter getDefaultAddressFormatter() {
      return this.defaultAddressFormatter;
   }

   public ItemClassIdentifiers getBestClassIdForAddress(long var1) {
      INativeContinuousItem var4 = this.pbcu.getNativeItemAt(var1);
      ItemClassIdentifiers var3;
      if (this.pbcu.Uv(var1)) {
         if (var4 instanceof axu) {
            var3 = ItemClassIdentifiers.EXTERNAL_METHOD_NAME;
         } else if (var4 instanceof axh) {
            var3 = ItemClassIdentifiers.FIELD_NAME;
         } else if (this.pbcu.RF(var1) != null) {
            var3 = ItemClassIdentifiers.METHOD_NAME;
         } else {
            var3 = ItemClassIdentifiers.LABEL;
         }
      } else {
         var3 = ItemClassIdentifiers.LABEL;
      }

      return var3;
   }

   public final long createItemIdForMnemonic(IInstruction var1) {
      return this.pbcu.q(var1);
   }

   public final long createItemIdForAddress(long var1) {
      return this.pbcu.gO(var1);
   }

   public final long createItemIdForImmediate(long var1, int var3) {
      return this.pbcu.q(var1, var3);
   }

   public long createItemIdForDefaultRegister(long var1) {
      return this.createItemIdForRegister(RegisterUtil.getPureId(var1));
   }

   public final long createItemIdForRegister(long var1) {
      return this.pbcu.nf(var1);
   }

   public void setDoNotAttemptImmediateToAddressResolution(boolean var1) {
      this.doNotResolveImmAsAddr = var1;
   }

   public boolean isDoNotAttemptImmediateToAddressResolution() {
      return this.doNotResolveImmAsAddr;
   }
}
