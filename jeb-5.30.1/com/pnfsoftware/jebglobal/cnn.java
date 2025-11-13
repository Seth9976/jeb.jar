package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionService;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class cnn extends AbstractNativeDisassemblerPlugin {
   public static final String q = "riscv";
   static ProcessorType RF;
   static ProcessorType xK;
   static ICallingConvention Dw;
   static ICallingConvention Uv;
   public static final String oW = "DisplayAbiRegisterNames";

   public cnn() {
      super("riscv", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("RISC-V Disassembler"), S.L("RV32GC/RV64GC disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return var1 == 243;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return var1 == 20530 || var1 == 20580;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(RF, xK);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      int var2 = 32;
      if (var1 instanceof ICodeObjectUnit) {
         var2 = ((ICodeObjectUnit)var1).getLoaderInformation().getWordSize();
      }

      return new cno(var2);
   }

   @Override
   public ICallingConvention getCallingConvention(IUnitCreator var1) {
      int var2 = 32;
      if (var1 instanceof ICodeObjectUnit) {
         var2 = ((ICodeObjectUnit)var1).getLoaderInformation().getWordSize();
      }

      if (var2 == 32) {
         return Dw;
      } else {
         return var2 == 64 ? Uv : null;
      }
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      int var2 = 32;
      if (var1 instanceof ICodeObjectUnit) {
         var2 = ((ICodeObjectUnit)var1).getLoaderInformation().getWordSize();
      }

      return VirtualMemoryUtil.createMemory(var2, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new cnk();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new cna();
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
      IPropertyDefinitionGroup var2 = var1.getGroup("text");
      var2.addDefinition(
         "DisplayAbiRegisterNames", PropertyTypeBoolean.create(true), S.L("Use abi (standard calling convention) registers instead of xN/fN"), 0
      );
      var2.addDefinition("RenderJRX1AsRet", PropertyTypeBoolean.create(false), S.L("Render 'JR X1 / JR LR' as 'RET' pseudo-instructions"), 0);
   }

   static {
      ProcessorType var0 = ProcessorType.valueOf(243);
      if (var0 == ProcessorType.UNKNOWN) {
         var0 = ProcessorType.register(243, "riscv", null);
      }

      RF = var0;
      var0 = ProcessorType.valueOf(61490);
      if (var0 == ProcessorType.UNKNOWN) {
         var0 = ProcessorType.register(61490, "riscv64", null);
      }

      xK = var0;
      RegisterBankService.getInstance().add(RF, cnp.Dw);
      RegisterBankService.getInstance().add(xK, cnp.Uv);
      Dw = new CallingConventionBuilder("ILP32D", RF)
         .setReturnAddressSlot(StorageEntry.createRegister(1L))
         .addInputSlot(StorageEntry.createRegister(10L))
         .addInputSlot(StorageEntry.createRegister(11L))
         .addInputSlot(StorageEntry.createRegister(12L))
         .addInputSlot(StorageEntry.createRegister(13L))
         .addInputSlot(StorageEntry.createRegister(14L))
         .addInputSlot(StorageEntry.createRegister(15L))
         .addInputSlot(StorageEntry.createRegister(16L))
         .addInputSlot(StorageEntry.createRegister(17L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(10L, 11L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(12L, 13L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(14L, 15L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(16L, 17L))
         .addInputSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(10L))
         .addOutputSlot(StorageEntry.createRegister(11L))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(10L, 11L))
         .build();
      Uv = new CallingConventionBuilder("LP64D", xK)
         .setReturnAddressSlot(StorageEntry.createRegister(1L))
         .addInputSlot(StorageEntry.createRegister(10L))
         .addInputSlot(StorageEntry.createRegister(11L))
         .addInputSlot(StorageEntry.createRegister(12L))
         .addInputSlot(StorageEntry.createRegister(13L))
         .addInputSlot(StorageEntry.createRegister(14L))
         .addInputSlot(StorageEntry.createRegister(15L))
         .addInputSlot(StorageEntry.createRegister(16L))
         .addInputSlot(StorageEntry.createRegister(17L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(10L, 11L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(12L, 13L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(14L, 15L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(16L, 17L))
         .addInputSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(10L))
         .addOutputSlot(StorageEntry.createRegister(11L))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(10L, 11L))
         .build();
      CallingConventionService.getInstance().addConvention(Dw);
      CallingConventionService.getInstance().addConvention(Uv);
      ELFPluginsService.getInstance().addRelocationContext(cnd.oW);
   }
}
