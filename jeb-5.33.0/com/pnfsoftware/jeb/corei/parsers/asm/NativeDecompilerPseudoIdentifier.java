package com.pnfsoftware.jeb.corei.parsers.asm;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CDocument;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.ace;
import com.pnfsoftware.jebglobal.ajd;
import java.util.Map;

public class NativeDecompilerPseudoIdentifier extends AbstractUnitIdentifier {
   private INativeDecompilerPlugin pC;

   public NativeDecompilerPseudoIdentifier(INativeDecompilerPlugin var1) {
      super(var1.getFormatType(), var1.getPriority());
      this.pC = var1;
   }

   public INativeDecompilerPlugin pC() {
      return this.pC;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return this.pC.getPluginInformation();
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, Strings.ff("Property overrides for the decompiler: %s", this.getFormatType()), 1);
      pC(this.pdm);
      this.pC.setupCustomProperties(this.pdm);
   }

   public static void pC(IPropertyDefinitionManager var0) {
      int var1 = Licensing.isDebugBuild() ? 0 : 180;
      var0.addDefinition(
         "MethodDecompilationTimeout",
         PropertyTypeInteger.createPositiveOrZero(var1),
         S.L("Maximum time in seconds allowed for a method decompilation (use 0 to specify no time-out)")
      );
      var0.addDefinition(
         "MemoryResolutionPolicy",
         PropertyTypeInteger.create(3),
         S.L("A resolution policy guides the memory resolver in terms of what memory dereferences can be directly resolved.")
      );
      var0.addDefinition(
         "ReconversionMaxCount",
         PropertyTypeInteger.createPositiveOrZero(5),
         S.L("Maximum decompilation pipeline restart (reconversion) allowed for a routine")
      );
      var0.addDefinition(
         "IROptimizerMaxRunCount",
         PropertyTypeInteger.create(10),
         S.L("Determine how many passes the IR optimizer may be allowed to run during an optimization task (0 means no limit)")
      );
      var0.addDefinition(
         "EnableDeobfuscators",
         PropertyTypeBoolean.create(true),
         S.L(
            "Allow deobfuscators to run. A deobfuscator is an optimizer whose primary purpose is to simplify code that was intentionally complicated by an obfuscating tool."
         )
      );
      var0.addDefinition(
         "EnableUnsafeOptimizers",
         PropertyTypeBoolean.create(true),
         S.L("Allow unsafe optimizers to run. Unsafe optimizers may make assumptions about the program state (memory and registers) and modify that state.")
      );
      var0.addDefinition(
         "DecryptorSupport",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, "Disabled", S.L("The automatic decryptor is disabled"))
            .addEntry(
               1,
               "Enabled",
               S.L(
                  "The automatic decryptor is enabled. The unsafe deobfuscators must be enabled as well. This optimizer makes use of the emulator and sandbox."
               )
            )
            .setDefault(1)
            .build(),
         S.L("Support for generic, emulator-backed decryption, allowing optimizers to perform complex code cleaning such as decryption"),
         8
      );
      var0.addDefinition(
         "ASTOptimizerMaxRunCount",
         PropertyTypeInteger.create(20),
         S.L("Determine how many passes the AST optimizer may be allowed to run during an optimization task (0 means no limit)")
      );
      var0.addDefinition(
         "StructurerUseVersion",
         PropertyTypeInteger.create(0, 3, 3),
         S.L("Version of the structurer to use (0 means use the default structuring algorithms - in release mode, default=newest)")
      );
      var0.addDefinition(
         "UseFriendlyVariableNames",
         PropertyTypeBoolean.create(true),
         S.L("Generate better identifier names (e.g., based on types or dictionaries) instead of generic names")
      );
      var0.addDefinition(
         "UseWellKnownLiterals",
         PropertyTypeBoolean.create(true),
         S.L("Whenever possible, generate constants using well-known literals instead of raw immediates")
      );
      var0.addDefinition(
         "NextDecompKeepIR",
         PropertyTypeBoolean.create(),
         S.L("Special non-sticky property used to specify that the IR context must be kept for the next decompilation")
      );
      var0.addDefinition(
         "EnableExternalPlugins",
         PropertyTypeBoolean.create(true),
         S.L(
            "Enable external plugins, such as custom IR/AST optimizers. If this option is disabled, user-made java/python plugins will not be instantiated by the decompiler."
         )
      );
      var0.addDefinition(
         "ListOfDisabledExternalPlugins",
         PropertyTypeString.create(),
         S.L("Comma-separated list of names of decompiler plugins that must be disabled (by default, a plugin is enabled)")
      );
      CDocument.buildPDM(var0);
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!(var2 instanceof C)) {
         return false;
      } else {
         String var5 = "dcmp_" + ((C)var2).getFormatType();
         return var5.equals(this.getFormatType());
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      C var6 = (C)var4;
      ace var7 = new ace(this.getFormatType(), var1, var3, var6, this.pdm);
      var7.pC(this.pC.getConverter(var6));
      var7.A(this.pC.getPrimaryExtension(var7));
      var7.pC(this.pC.getGlobalAnalyzer(var7));
      var7.pC(this.pC.getSourceCustomizer(var7));
      var6.addChild(var7);
      var7.process();
      return var7;
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (var1 instanceof String && ((String)var1).equals("DGRM") && var2 instanceof Long) {
         try {
            long var3 = (Long)var2;
            if ((var3 & -16L) == (Licensing.license_id & -16L)) {
               int var5 = ((int)var3 & 15) % 11;
               ajd.A = var5;
               return;
            }
         } catch (Exception var6) {
         }
      }

      super.setData(var1, var2);
   }
}
