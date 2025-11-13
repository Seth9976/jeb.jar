package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips64;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class Mips64Plugin extends AbstractNativeDisassemblerPlugin {
   public Mips64Plugin() {
      super("mips64", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("MIPS64"), S.L("MIPS64 generic disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return var2 && var1 == 8;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return false;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.MIPS64);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new p(64, var1);
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(64, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new Ws();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return null;
   }

   static {
      RegisterBankService.getInstance().add(ProcessorType.MIPS64, RegisterBankMips64.getInstance());
   }
}
