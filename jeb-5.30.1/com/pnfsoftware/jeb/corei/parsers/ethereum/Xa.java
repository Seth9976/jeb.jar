package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionService;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class Xa extends AbstractNativeDisassemblerPlugin {
   public static final String q = "evmbc";
   static ProcessorType RF;
   static ICallingConvention xK;

   public Xa() {
      super("evmbc", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("EVM Disassembler"), S.L("Ethereum Contract bytecode disassembler"), "PNF Software", Version.create(0, 3));
   }

   @Override
   public boolean canBeProcessedOutsideCodeObject() {
      return false;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(RF);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      if (var1 instanceof PY) {
         Bu var2 = ((PY)var1).q();
         if (var2 == null) {
            throw new JebRuntimeException("Is parent of evmbc unit processed? Process parent first");
         } else {
            return var2;
         }
      } else {
         return new Bu();
      }
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(256, 12, Endianness.BIG_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new bK();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new Vj();
   }

   static {
      ProcessorType var0 = ProcessorType.valueOf(61441);
      if (var0 == ProcessorType.UNKNOWN) {
         var0 = ProcessorType.register(61441, "evm", null);
      }

      RF = var0;
      RegisterBankService.getInstance().add(RF, LR.q);
      xK = new CallingConventionBuilder(CallingConventionName.CDECL, RF)
         .addAlternateName("__solint")
         .setFlags(641)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addInputSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createStackSlot(0L))
         .build();
      CallingConventionService.getInstance().addConvention(xK);
   }
}
