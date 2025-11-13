package com.pnfsoftware.jeb.corei.parsers.arm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm64;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jebglobal.pQ;
import java.util.Arrays;
import java.util.List;

public class Arm64Plugin extends AbstractNativeDisassemblerPlugin {
   public Arm64Plugin() {
      super("arm64", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Aarch64"), S.L("ARM 64 generic disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return var2 && var1 == 183;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return var2 && var1 == 43620;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.ARM64);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      return new cq(64, var1);
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(64, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new Sv();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return null;
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
      String var2 = "https://documentation-service.arm.com/";
      var1.addDefinition(
         "DocumentationRoot",
         PropertyTypeString.create(var2),
         Strings.ff(
            S.L("Location of ARM documentation files. Uses %s by default, which can be accessed at https://developer.arm.com/documentation/dui0801/l/"), var2
         ),
         72
      );
   }

   static {
      RegisterBankService.getInstance().add(ProcessorType.ARM64, RegisterBankArm64.getInstance());
      ELFPluginsService.getInstance().addRelocationContext(pQ.xC);
      ELFPluginsService.getInstance().addRelocationContext(pQ.Sc);
      ELFPluginsService.getInstance().addRelocationContext(pQ.ED);
      ELFPluginsService.getInstance().addRelocationContext(pQ.ah);
   }
}
