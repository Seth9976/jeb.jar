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

public class oL extends AbstractNativeDisassemblerPlugin {
   public static final String q = "simatic_mc7";
   static ProcessorType RF;
   public static final String xK = "__FB_CC";
   public static final String Dw = "__FC_CC";
   static ICallingConvention Uv;
   static ICallingConvention oW;

   public oL() {
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
      return Arrays.asList(RF);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new Vj();
   }

   @Override
   public ICallingConvention getCallingConvention(IUnitCreator var1) {
      return oW;
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.BIG_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new vn();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new eo();
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
   }

   public static boolean q() {
      return true;
   }

   static {
      ProcessorType var0 = ProcessorType.valueOf(61510);
      if (var0 == ProcessorType.UNKNOWN) {
         var0 = ProcessorType.register(61510, "mc7", null);
      }

      RF = var0;
      RegisterBankService.getInstance().add(RF, Bu.q);
      Uv = new CallingConventionBuilder("__FB_CC", RF)
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("rDI").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("AR2").getId()))
         .addOutputSlot(StorageEntry.createRegister(0L))
         .setReturnAddressSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("LR").getId()))
         .addSpoiledRegister(Bu.q.getDescriptionEntryByName("ACCU1").getId())
         .addSpoiledRegister(Bu.q.getDescriptionEntryByName("ACCU2").getId())
         .build();
      CallingConventionService.getInstance().addConvention(Uv);
      oW = new CallingConventionBuilder("__FC_CC", RF)
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR0").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR1").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR2").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR3").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR4").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR5").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR6").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR7").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR8").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR9").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR10").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR11").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR12").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR13").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR14").getId()))
         .addInputSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("PAR15").getId()))
         .addOutputSlot(StorageEntry.createRegister(0L))
         .setReturnAddressSlot(StorageEntry.createRegister(Bu.q.getDescriptionEntryByName("LR").getId()))
         .build();
      CallingConventionService.getInstance().addConvention(oW);
   }
}
