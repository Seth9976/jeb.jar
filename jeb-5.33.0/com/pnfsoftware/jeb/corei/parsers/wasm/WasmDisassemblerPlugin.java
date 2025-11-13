package com.pnfsoftware.jeb.corei.parsers.wasm;

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
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.util.io.Endianness;

public class WasmDisassemblerPlugin extends AbstractNativeDisassemblerPlugin {
   public WasmDisassemblerPlugin() {
      super("wasmbc", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("WebAssembly Disassembler"), S.L("WebAssembly bytecode disassembler"), "PNF Software", Version.create(1, 1));
   }

   @Override
   public boolean canBeProcessedOutsideCodeObject() {
      return false;
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      if (var1 instanceof Hv) {
         gJ var2 = ((Hv)var1).pC();
         if (var2 == null) {
            throw new JebRuntimeException("Is parent of wasmbc unit processed? Process parent first");
         } else {
            return var2;
         }
      } else {
         return new gJ();
      }
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new Pj();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new ma();
   }
}
