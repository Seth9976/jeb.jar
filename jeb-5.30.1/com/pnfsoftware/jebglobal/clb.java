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
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class clb extends AbstractNativeDisassemblerPlugin {
   public static final String q = "mips";

   public clb() {
      super("mips", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("MIPS"), S.L("MIPS generic disassembler (Mips I to V + Mips32)"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return !var2 && var1 == 8;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      if (!var2) {
         switch (var1) {
            case 352:
            case 354:
            case 358:
            case 360:
            case 361:
            case 870:
               return true;
         }
      }

      return false;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.MIPS);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new clc(32, var1);
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new ckm();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return null;
   }

   static {
      RegisterBankService.getInstance().add(ProcessorType.MIPS, RegisterBankMips.getInstance());
      ELFPluginsService.getInstance().addRelocationContext(ckp.za);
      ELFPluginsService.getInstance().addRelocationContext(ckp.lm);
   }
}
