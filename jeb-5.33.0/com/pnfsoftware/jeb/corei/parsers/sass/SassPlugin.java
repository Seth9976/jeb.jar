package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SassPlugin extends AbstractNativeDisassemblerPlugin {
   public SassPlugin() {
      super("sass_visa", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("SASS Disassembler"),
         S.L("NVidia SASS (Streaming Assembly) disassembler for sm_70+, referred to as 'sass_visa' (Volta+ ISA)"),
         "PNF Software",
         Version.create(0, 1)
      );
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return var1 == 190;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return false;
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(vi.pC);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      boolean var3;
      int var4;
      if (var1 instanceof IELFUnit var5) {
         int var6 = var5.getHeader().getFlags();
         var4 = var6 & 0xFF;
         if (var4 < 10) {
            var4 = var6 >> 8 & 0xFF;
            var3 = true;
         } else {
            var3 = (var6 & 1024) != 0;
         }
      } else {
         IRuntimeProject var9 = RuntimeProjectUtil.findProject(var1);
         String var7 = ".parsers.native.disas.sass_visa.DefaultParserVersion";
         var4 = var9.getPropertyManager().getInteger(var7);
         var3 = true;
      }

      if (var4 < 70) {
         throw new RuntimeException(Strings.ff("Cannot process this CUDA cc: sm_%d (minimum required: sm_70)", var4));
      } else {
         if (var4 == 70) {
            var4 = 70;
         }

         byte var8;
         if (var4 <= 72) {
            var8 = 72;
         } else if (var4 <= 75) {
            var8 = 75;
         } else if (var4 <= 80) {
            var8 = 80;
         } else if (var4 <= 86) {
            var8 = 86;
         } else if (var4 <= 87) {
            var8 = 87;
         } else if (var4 <= 89) {
            var8 = 89;
         } else if (var4 <= 90) {
            var8 = 90;
         } else if (var4 <= 100) {
            var8 = 100;
         } else if (var4 <= 101) {
            var8 = 101;
         } else if (var4 <= 103) {
            var8 = 103;
         } else if (var4 <= 120) {
            var8 = 120;
         } else if (var4 <= 121) {
            var8 = 121;
         } else {
            var8 = 121;
         }

         String var2 = "sm_" + var8;
         return new vi(var2, var3 ? 64 : 32);
      }
   }

   @Override
   public ICallingConvention getCallingConvention(IUnitCreator var1) {
      return vi.kS;
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      byte var2 = 64;
      return VirtualMemoryUtil.createMemory(var2, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new RC();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return new cq();
   }

   @Override
   public Collection createContributions(INativeCodeUnit var1) {
      return List.of(new DH(var1));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
      var1.addDefinition(
         "DefaultParserVersion",
         PropertyTypeInteger.create(75),
         Strings.ff(
            S.L("The compute capability the SASS parser should use if it cannot be automatically determined. Input the number N of the 'sm_N' cc string.")
         ),
         0
      );
      boolean var2 = JebCoreService.getExistingInstance().getOptions().isUIClient();
      IPropertyDefinitionGroup var3 = var1.getGroup("text");
      var3.addDefinition(
         "DisplayImplicitDescriptors",
         PropertyTypeBoolean.create(var2),
         S.L("Display the implicit descriptor (override a 'noexp_desc' attribute) for clarity"),
         0
      );
      var3.addDefinition(
         "DisplayRegisterNumbers",
         PropertyTypeBoolean.create(false),
         S.L("Generate number-based for registers names instead of aliases (e.g. P7 instead of PT)"),
         0
      );
      var3.addDefinition(
         "DisplayHiddenAttributes",
         PropertyTypeBoolean.create(false),
         S.L("Generate all opcode and operand attributes, even if they use default values and should be hidden"),
         0
      );
      var3.addDefinition("DisplaySchedulingInfo", PropertyTypeBoolean.create(false), S.L("Generate explicit instruction scheduling information"), 0);
   }

   static {
      vi.pC();
   }
}
