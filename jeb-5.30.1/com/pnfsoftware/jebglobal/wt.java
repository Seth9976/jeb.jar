package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.List;

public class wt extends AbstractNativeDisassemblerPlugin {
   public static final String q = "arm";
   public static final String RF = "DefaultMode";
   public static final String xK = "ForceWideFlag";
   public static final String Dw = "DocumentationRoot";
   public static final int Uv = 0;
   public static final int oW = 1;
   public static final int gO = 2;
   public static final int nf = 0;
   public static final int gP = 32;
   public static final int za = 16;

   public wt() {
      super("arm", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("ARM"), S.L("ARM generic disassembler"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return !var2 && var1 == 40;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return !var2 && (var1 == 448 || var1 == 450 || var1 == 452);
   }

   @Override
   public List getProcessorTypes() {
      return Arrays.asList(ProcessorType.ARM);
   }

   @Override
   public IProcessor getProcessor(IUnitCreator var1) {
      int var2 = 0;
      if (this.pm != null) {
         var2 = this.pm.getInteger("DefaultMode", 0);
      }

      if (var2 == 0) {
         var2 = this.q(var1);
      }

      return new vh(var2, var1);
   }

   private int q(IUnitCreator var1) {
      byte var2 = 32;
      if (var1 instanceof AbstractCodeObjectUnit var3) {
         List var4 = var3.getExportedSymbols();
         int var5 = 0;
         int var6 = 0;
         if (var4 != null) {
            for (ISymbolInformation var8 : var4) {
               if (var8.getType() == SymbolType.FUNCTION || var8.getType() == SymbolType.FUNCTION_MAYBE) {
                  if ((var8.getSymbolRelativeAddress() & 1L) == 1L) {
                     var5++;
                  } else {
                     var6++;
                  }
               }
            }
         }

         if ((var3.getLoaderInformation().getEntryPoint() & 1L) == 1L) {
            var5++;
         } else {
            var6++;
         }

         if ((var5 > 10 || var6 > 10) && var5 > var6 * 5) {
            var2 = 16;
         }
      }

      return var2;
   }

   @Override
   public IVirtualMemory getMemory(IUnitCreator var1) {
      return VirtualMemoryUtil.createMemory(32, 12, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return new FE();
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return null;
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
      var1.addDefinition(
         "PerformFakeRoutineCallAnalysis",
         PropertyTypeBoolean.create(Boolean.TRUE),
         S.L("Search for Branch and Link (BL) instructions that should be considered as simple branches rather than routine calls (Thumb code optimization)")
      );
      var1.addDefinition(
         "DefaultMode",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("Default mode"), S.L("Auto-detect ARM Mode using parent symbols"))
            .addEntry(32, "ARM", S.L("Use ARM Mode in priority"))
            .addEntry(16, "Thumb", S.L("Use Thumb Mode in priority"))
            .setDefault(0)
            .build(),
         S.L("Default plugin mode (ARM or Thumb)")
      );
      String var2 = "https://documentation-service.arm.com/";
      var1.addDefinition(
         "DocumentationRoot",
         PropertyTypeString.create(var2),
         Strings.ff(
            S.L(
               "Location of ARM documentation files. Uses %s by default, which can be accessed via navigator at https://developer.arm.com/documentation/dui0801/l/"
            ),
            var2
         ),
         72
      );
      IPropertyDefinitionGroup var3 = var1.getGroup("text");
      var3.addDefinition(
         "ForceWideFlag",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, "Display All", "Display .W flag on all 32-bit instruction")
            .addEntry(1, "Distinguish 16-bit from 32-bit", "Display .W flag only when a 16-bit instruction exists (even if there is no equivalent in 16-bit)")
            .addEntry(2, "Display when forced", "Display .W flag only when a 16-bit equivalent exists (Wide version was forced)")
            .setDefault(0)
            .build(),
         "Wide Flag Display (T32 only)"
      );
   }

   static {
      RegisterBankService.getInstance().add(ProcessorType.ARM, RegisterBankArm.getInstance());
      ELFPluginsService.getInstance().addRelocationContext(Ra.qa);
      ELFPluginsService.getInstance().addRelocationContext(Ra.Hk);
      ELFPluginsService.getInstance().addRelocationContext(Ra.Me);
      ELFPluginsService.getInstance().addRelocationContext(Ra.PV);
      ELFPluginsService.getInstance().addSectionProcessor(new Gy());
      ELFPluginsService.getInstance().addSymbolsProcessorFactory(new lY());
   }
}
