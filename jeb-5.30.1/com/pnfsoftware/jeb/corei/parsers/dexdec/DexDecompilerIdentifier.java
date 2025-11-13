package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.java.JavaDocument;
import com.pnfsoftware.jeb.corei.parsers.dex.bK;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.ITypeIdProvider;
import com.pnfsoftware.jebglobal.bth;
import com.pnfsoftware.jebglobal.cvm;
import java.util.Map;

public class DexDecompilerIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(DexDecompilerIdentifier.class);
   public static final String emuConfigName = "dexdec-emu.cfg";
   public static final String propnameMethodDecompilationTimeout = "MethodDecompilationTimeout";
   public static final String propnameDecompilerThreadCount = "DecompilerThreadCount";
   public static final String propnameDecompileTopLevelContainerClass = "DecompileTopLevelContainerClass";
   public static final String propnameParseExceptionBlocks = "ParseExceptionBlocks";
   public static final String propnameParseDebugInformation = "ParseDebugInformation";
   public static final String propnameDeferredRequestsCap = "DeferredRequestsCap";
   public static final String propnameEnableUnsafeOptimizers = "EnableUnsafeOptimizers";
   public static final String propnameEnableDeobfuscatorOptimizers = "EnableDeobfuscatorOptimizers";
   public static final String propnameEnableInlinerOptimizers = "EnableInlinerOptimizers";
   public static final String propnameEnableCollectionOptimizers = "EnableCollectionOptimizers";
   public static final String propnameDecryptorSupport = "DecryptorSupport";
   public static final String propnameAggressiveCodeCleanup = "AggressiveCodeCleanup";
   public static final String propnameEnablePredicateBreaker = "EnablePredicateBreaker";
   public static final String propnameEmulatorConfigPath = "EmulatorConfigPath";
   public static final String propnameEnableCacheForStringDecryption = "EnableCacheForStringDecryption";
   public static final String propnameEnableUnvirtualizer = "EnableUnvirtualizer";
   public static final String propnameEnableCFUnflattener = "EnableCFUnflattener";
   public static final String propnameEnableTryWithResRebuilder = "EnableARMRebuilder";
   public static final String propnameEnableTryFinallyRebuilder = "EnableFinallyRebuilder";
   public static final String propnameEnableExternalPlugins = "EnableExternalPlugins";
   public static final String propnameListOfDisabledExternalPlugins = "ListOfDisabledExternalPlugins";
   public static final String propnameStructurerUseVersion = "StructurerUseVersion";
   public static final String propnameIdentifierNamingStrategy = "IdentifierNamingStrategy";
   public static final String propnameGenerateSpecialMetaComments = "GenerateSpecialMetaComments";
   public static final String propnameDGVAnalysisUnlockKey = cvm.q(
      new byte[]{7, 40, 38, 56, 28, 8, 11, 17, 7, 73, 91, 54, 71, 76, 94, 90, 82, 120, 73, 89}, 2, 237
   );
   public static final String TYPE = "dcmp_dex";

   public DexDecompilerIdentifier() {
      super("dcmp_dex", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Dex Decompiler"), S.L("Dalvik decompiler"), "Nicolas Falliere", Version.create(1, 2, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, S.L("Properties of the Dex Decompiler"), 0);
      int var2 = Licensing.isDebugBuild() ? 0 : 60;
      this.pdm
         .addDefinition(
            "MethodDecompilationTimeout",
            PropertyTypeInteger.createPositiveOrZero(var2),
            S.L("Maximum time in seconds allowed for a method decompilation (use 0 to specify no time-out)")
         );
      this.pdm
         .addDefinition(
            "DecompilerThreadCount",
            PropertyTypeInteger.create(0),
            S.L(
               "A hint of the count of threads used for concurrent decompilations (the default is 0, a special value that means 'half of the system's number of logical processors'; the special value -1 means 'all logical processors'; the value 1 can be used to disable concurrent decompilations entirely)"
            )
         );
      this.pdm
         .addDefinition(
            "DecompileTopLevelContainerClass",
            PropertyTypeBoolean.create(true),
            S.L(
               "When requesting a decompilation at a particular location A, determine the top-level non-inner class at A, and decompile that class and all its constituents (methods, inners, etc.).\nIf this option is disabled, precise decompilation will be performed: the requested address will not be reinterpreted, and only the target object will be decompiled. Example: decompiling while on a method F's bytecode will decompile F and F only (not its container class, not its inners, etc.)."
            )
         );
      this.pdm.addDefinition("ParseExceptionBlocks", PropertyTypeBoolean.create(true), S.L("Parse exception blocks"));
      this.pdm
         .addDefinition(
            "ParseDebugInformation",
            PropertyTypeBoolean.create(true),
            S.L(
               "Process debug information. If enabled, debug names can be used to name identifiers.\nProceed with caution! Dex metadata is not reliable, it can be forged and/or obfuscated to mislead analysis."
            )
         );
      this.pdm
         .addDefinition(
            "EnableUnsafeOptimizers",
            PropertyTypeBoolean.create(true),
            S.L(
               "Unsafe optimizers are aggressive optimizers that may change the resulting code more than necessary. This setting is used by IR and AST master optimizers."
            )
         );
      this.pdm
         .addDefinition(
            "EnableDeobfuscatorOptimizers",
            PropertyTypeBoolean.create(true),
            S.L("Deobfuscator optimizers can generate code radically different than the underlying bytecode. This setting is used by IR optimizers only.")
         );
      this.pdm
         .addDefinition(
            "EnableInlinerOptimizers",
            PropertyTypeBoolean.create(true),
            S.L("Inliner optimizers perform selective inlining of method invocations. This setting is used by IR optimizers only.")
         );
      this.pdm
         .addDefinition(
            "EnableCollectionOptimizers",
            PropertyTypeBoolean.create(false),
            S.L(
               "Collection optimizers work on groups of IRs, representing decompiled methods of a class and its inner classes, on a best-effort basis. This setting is used by IR optimizers only."
            )
         );
      this.pdm
         .addDefinition(
            "DecryptorSupport",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, S.L("Disabled"), S.L("The automatic decryptor is disabled"))
               .addEntry(
                  1,
                  S.L("Enabled"),
                  S.L(
                     "The automatic decryptor is enabled. The unsafe deobfuscators must be enabled as well. This optimizer makes use of the emulator and sandbox. You may configure their properties via the dexdec-emu.cfg file."
                  )
               )
               .addEntry(
                  2,
                  S.L("Enabled, no static-reads"),
                  S.L(
                     "The automatic decryptor is enabled in some cases. If the decryption requires getting the value of a dex (internal) static field that may be written elsewhere, it will fail."
                  )
               )
               .setDefault(1)
               .build(),
            S.L("Support for generic, emulator-backed decryption, allowing optimizers to perform complex code cleaning such as decryption"),
            8
         );
      this.pdm
         .addDefinition(
            "AggressiveCodeCleanup",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, S.L("Disabled"), S.L("Aggressive clean-up is disabled"))
               .addEntry(1, S.L("Limited"), S.L("Aggressive clean-up is enabled for specially vetted cases, e.g. code stubs looking side-effect-free, etc."))
               .addEntry(2, S.L("Maximal"), S.L("Aggressive clean-up is generally enabled, with checks less stringent than with the Limited setting"))
               .setDefault(1)
               .build(),
            S.L(
               "This setting can be used by deobfuscator optimizers (unsafe) to determine what amount of code deemed 'useless' they may be allowed to clean-up"
            ),
            8
         );
      this.pdm
         .addDefinition(
            "DeferredRequestsCap",
            PropertyTypeInteger.create(400),
            S.L(
               "Limit the amount of deferred decompilation requests (-1 means no limit). Usually, deferred requests are for methods or classes related to the original request, but not part of it. Deferred requests are issued by some optimizers to generate better code."
            )
         );
      this.pdm
         .addDefinition(
            "EmulatorConfigPath",
            PropertyTypeString.create("dexdec-emu.cfg"),
            S.L(
               "Path to the emulator configuration file. If the path is relative, the configuration file will be searched in JEB's plugins directory (usually, coreplugins/)."
            )
         );
      this.pdm
         .addDefinition(
            "EnableCacheForStringDecryption",
            PropertyTypeBoolean.create(true),
            S.L(
               "The emulation cache is used to store and reuse the results of select, previously emulated invocations.\nIt works using heuristics, and is theoretically unsafe (a cached entry may be incorrect).\nIn the general case, it is relatively safe to use and should speed up emulation results.\nIf problems show up on a specific file/method, disable it and attempt a redecompilation."
            )
         );
      this.pdm
         .addDefinition(
            "EnablePredicateBreaker",
            PropertyTypeBoolean.create(true),
            S.L("Support for breaking opaque predicates, to determine conditional paths that would never be executed, and allow clean-up")
         );
      this.pdm
         .addDefinition("EnableCFUnflattener", PropertyTypeBoolean.create(true), S.L("Enable control-flow unflattening. The obfuscators must be enabled."));
      this.pdm
         .addDefinition(
            "EnableUnvirtualizer",
            PropertyTypeBoolean.create(true),
            S.L("Enable unvirtualization of methods and classes protected by a VM. The obfuscators must be enabled, and your license must support this option.")
         );
      this.pdm
         .addDefinition(
            "EnableARMRebuilder",
            PropertyTypeBoolean.create(true),
            S.L("Enable the optimizer that will attempt to rebuild try-with-resources (ARM) constructs.")
         );
      this.pdm
         .addDefinition(
            "EnableFinallyRebuilder", PropertyTypeBoolean.create(true), S.L("Enable the optimizer that will attempt to rebuild try-finally constructs.")
         );
      this.pdm
         .addDefinition(
            "EnableExternalPlugins",
            PropertyTypeBoolean.create(true),
            S.L(
               "Enable external plugins, such as custom IR/AST optimizers. If this option is disabled, user-made java/python plugins will not be instantiated by the decompiler."
            )
         );
      this.pdm
         .addDefinition(
            "ListOfDisabledExternalPlugins",
            PropertyTypeString.create(),
            S.L("Comma-separated list of names of decompiler plugins that must be disabled (by default, a plugin is enabled)")
         );
      this.pdm
         .addDefinition(
            "GenerateSpecialMetaComments",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, S.L("Disabled"), S.L("Do not generate special comments"))
               .addEntry(
                  1, S.L("Some"), S.L("Generate some special comments for optimizers that restructured the code in depth (unflattener, unvirtualizer, etc.)")
               )
               .addEntry(9, S.L("All"), S.L("Generate all special comments"))
               .setDefault(1)
               .build(),
            S.L("Generate header comments for decompiled methods when notable optimizers performed clean-up or deobfuscation"),
            8
         );
      this.pdm.addInternalDefinition("StructurerUseVersion", PropertyTypeInteger.create(0, 3, 3));
      this.pdm
         .addDefinition(
            "IdentifierNamingStrategy",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, S.L("Register-based"), S.L("Reflect underlying register usage as much as possible: argN, vN, vN_x"))
               .addEntry(1, S.L("Type-based"), S.L("Derive names from types"))
               .setDefault(Licensing.isDebugBuild() ? 0 : 1)
               .build(),
            S.L("Naming strategy for the identifiers"),
            8
         );
      this.pdm.addInternalDefinition(propnameDGVAnalysisUnlockKey, PropertyTypeString.create());
      JavaDocument.buildPDM(this.pdm);
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return var2 instanceof bK;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      if (!(var4 instanceof bK var6)) {
         throw new IllegalArgumentException();
      } else {
         ej var7 = new ej(var1, var2, var3, var6, this.pdm);
         var6.addChild(var7);
         var7.process();
         return var7;
      }
   }

   @Override
   public ITypeIdProvider getTypeIdProvider() {
      return nI.q();
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (var1 instanceof String && ((String)var1).equals("DGRM") && var2 instanceof Long) {
         try {
            long var3 = (Long)var2;
            if ((var3 & -16L) == (Licensing.license_id & -16L)) {
               int var5 = ((int)var3 & 15) % 11;
               bth.xK = var5;
               return;
            }
         } catch (Exception var6) {
         }
      }

      super.setData(var1, var2);
   }
}
