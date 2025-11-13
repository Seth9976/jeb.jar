package com.pnfsoftware.jebglobal;

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
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX64;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class cri extends AbstractNativeDisassemblerPlugin {
   public static final String q = "x86_64";

   public cri() {
      super("x86_64", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("x86-64 Disassembler"), S.L("Intel/AMD x86 64-bit (x64) disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return var2 && var1 == 62;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return var2 && var1 == 34404;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.X86_64);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new cti(64);
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(64, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new crn();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return null;
   }

   static {
      RegisterBankService.getInstance().add(ProcessorType.X86_64, RegisterBankX64.getInstance());
      ELFPluginsService.getInstance().addRelocationContext(cql.Me);
      ELFPluginsService.getInstance().addRelocationContext(cql.PV);
      ELFPluginsService.getInstance().addRelocationContext(cql.oQ);
      ELFPluginsService.getInstance().addRelocationContext(cql.xW);
   }
}
