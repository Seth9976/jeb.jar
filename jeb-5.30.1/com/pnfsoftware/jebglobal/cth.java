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
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class cth extends AbstractNativeDisassemblerPlugin {
   public static final String q = "x86";

   public cth() {
      super("x86", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("x86 Disassembler"), S.L("Intel/AMD x86 32-bit disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return !var2 && var1 == 3;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return !var2 && var1 == 332;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.X86);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new cti(32);
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.LITTLE_ENDIAN);
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
      RegisterBankService.getInstance().add(ProcessorType.X86, RegisterBankX86.getInstance());
      ELFPluginsService.getInstance().addRelocationContext(csb.qa);
      ELFPluginsService.getInstance().addRelocationContext(csb.Hk);
      ELFPluginsService.getInstance().addRelocationContext(csb.PV);
      ELFPluginsService.getInstance().addRelocationContext(csb.oQ);
      ELFPluginsService.getInstance().addRelocationContext(csb.Me);
   }
}
