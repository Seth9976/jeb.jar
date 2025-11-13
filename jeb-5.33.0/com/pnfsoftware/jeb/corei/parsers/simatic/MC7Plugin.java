package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
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
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class MC7Plugin extends AbstractNativeDisassemblerPlugin {
   static ProcessorType pC;
   static ICallingConvention A;
   static ICallingConvention kS;

   public MC7Plugin() {
      super("simatic_mc7", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("S7 Disassembler"), S.L("S7 PLC block disassembler (for Simatic S7 blocks, MC7 bytecode)"), "PNF Software", Version.create(0, 1)
      );
   }

   @Override
   public boolean canBeProcessedOutsideCodeObject() {
      return true;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(pC);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new sy();
   }

   @Override
   public ICallingConvention getCallingConvention(IUnitCreator var1) {
      return kS;
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.BIG_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new KD();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new Av();
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
   }

   static {
      ProcessorType var0 = ProcessorType.valueOf(61510);
      if (var0 == ProcessorType.UNKNOWN) {
         var0 = ProcessorType.register(61510, "mc7", null);
      }

      pC = var0;
      RegisterBankService.getInstance().add(pC, HE.pC);
      A = new CallingConventionBuilder("__FB_CC", pC)
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("rDI").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("AR2").getId()))
         .addOutputSlot(StorageEntry.createRegister(0L))
         .setReturnAddressSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("LR").getId()))
         .addSpoiledRegister(HE.pC.getDescriptionEntryByName("ACCU1").getId())
         .addSpoiledRegister(HE.pC.getDescriptionEntryByName("ACCU2").getId())
         .build();
      CallingConventionService.getInstance().addConvention(A);
      kS = new CallingConventionBuilder("__FC_CC", pC)
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR0").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR1").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR2").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR3").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR4").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR5").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR6").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR7").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR8").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR9").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR10").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR11").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR12").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR13").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR14").getId()))
         .addInputSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("PAR15").getId()))
         .addOutputSlot(StorageEntry.createRegister(0L))
         .setReturnAddressSlot(StorageEntry.createRegister(HE.pC.getDescriptionEntryByName("LR").getId()))
         .build();
      CallingConventionService.getInstance().addConvention(kS);
   }
}
