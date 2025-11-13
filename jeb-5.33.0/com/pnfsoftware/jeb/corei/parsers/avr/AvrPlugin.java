package com.pnfsoftware.jeb.corei.parsers.avr;

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
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class AvrPlugin extends AbstractNativeDisassemblerPlugin {
   public AvrPlugin() {
      super("avr", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("AVR Disassembler"), S.L("Atmel AVR disassembler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.AVR);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new RC();
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 8, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new Sv();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new Av();
   }
}
