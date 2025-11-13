package com.pnfsoftware.jeb.corei.parsers.asm;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.corei.parsers.elf.ELFIdentifier;
import com.pnfsoftware.jeb.corei.parsers.winpe.PEIdentifier;
import com.pnfsoftware.jeb.corei.parsers.x86.wn;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.axz;
import com.pnfsoftware.jebglobal.iL;
import java.nio.ByteBuffer;
import java.util.Map;

public class NativeCodePseudoIdentifier extends AbstractUnitIdentifier {
   private INativeDisassemblerPlugin pC;

   public NativeCodePseudoIdentifier(INativeDisassemblerPlugin var1) {
      super(var1.getFormatType(), var1.getPriority());
      this.pC = var1;
   }

   public INativeDisassemblerPlugin pC() {
      return this.pC;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return this.pC.getPluginInformation();
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, Strings.ff(S.L("Property overrides for the disassembler: %s"), this.getFormatType()), 1);
      pC(this.pdm);
      this.pC.setupCustomProperties(this.pdm);
   }

   public static void pC(IPropertyDefinitionManager var0) {
      PropertyTypeSelection.Builder var1 = PropertyTypeSelection.Builder.create();

      for (CallingConventionName var3 : CallingConventionName.values()) {
         var1.addEntry(var3.id(), var3.toString(), null);
      }

      var0.addDefinition("CallingConvention", var1.build(), S.L("Default calling convention to use. Leave unknown for automatic detection"));
      var0.addDefinition(
         "Endianness",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Automatic"), null)
            .addEntry(1, S.L("Little-endian"), null)
            .addEntry(2, S.L("Big-endian"), null)
            .build(),
         S.L("Reserved for raw binaries: Wanted endianness (may be overridden by the code parser).")
      );
      var0.addDefinition(
         "ImageBase",
         PropertyTypeString.create(""),
         Strings.ff(
            S.L(
               "If unspecified, the base address from the code object (ELF, PE, MachO...) is used. If none can be found (eg, raw binaries) default address is 0. Also see the related property %s"
            ),
            "RelocationBaseForZeroBasedRelocatableObjects"
         ),
         32
      );
      var0.addDefinition(
         "RelocationBaseForZeroBasedRelocatableObjects",
         PropertyTypeString.create("10000h"),
         S.L("An object specifying no base (typically, a relocatable library file, such as an .so/.dll file) will be relocated and mapped to this address"),
         32
      );
      var0.addDefinition(
         "DelayAnalysis",
         PropertyTypeBoolean.create(false),
         S.L(
            "Delay the analysis until an explicit Analysis action is requested by the user (as opposed to starting binary analysis right after the artifact is opened)"
         )
      );
      var0.addDefinition(
         "AnalysisStyle",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("Conservative"), S.L("Forced conservative (code gaps=prologues starting only, data gaps=default analysis)"))
            .addEntry(1, S.L("Automatic"), S.L("The selected mode depends on the input file"))
            .addEntry(2, S.L("Aggressive"), S.L("Forced aggressive (code gaps=linear sweep analysis, data gaps=default analysis)"))
            .addEntry(3, S.L("Lazy"), S.L("Forced lazy (code gaps=no analysis, data gaps=default analysis)"))
            .addEntry(4, S.L("Lazy no data"), S.L("Forced lazy, no data (code gaps=no analysis, data gaps=no analysis)"))
            .setDefault(Licensing.isReleaseBuild() ? 0 : 1)
            .build(),
         null
      );
      var0.addDefinition(
         "AdvancedAnalysis",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Disabled"), S.L("No advanced analysis"))
            .addEntry(1, S.L("Only For Small Binaries"), S.L("Advanced analysis will run when code sections size is less than 1MB"))
            .addEntry(2, S.L("Always"), S.L("Advanced analysis will always run"))
            .build(),
         S.L(
            "The advanced analysis is an optional pass that comes after the initial (standard) analysis. It permits discovery of indirect dispatch, therefore providing better routine discovery coverage, and partial resolution of register values."
         )
      );
      var0.addDefinition(
         "PerformRttiRecovery",
         PropertyTypeBoolean.create(true),
         S.L("Attempt C++ Run-Time Type Information (RTTI) discovery and rebuilding (MSVC and Itanium (GCC/CLANG/NDK) are supported)")
      );
      var0.addDefinition(
         "PerformGlobalAnalysis",
         PropertyTypeBoolean.create(!Licensing.isDebugBuild()),
         S.L(
            "Global analysis passes are optional passes provided by specific decompiler modules. They may allow recovery of complex artifacts, such as classes or modules."
         )
      );
      var0.addDefinition(
         "ForceSynchronousAnalysis",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(-1, S.L("Default"), S.L("Will default to non-blocking for GUI clients; blocking for headless clients"))
            .addEntry(0, S.L("Non-blocking"), S.L("Asynchronous analysis is preferred"))
            .addEntry(1, S.L("Blocking"), S.L("Synchronous analysis is preferred"))
            .build(),
         S.L(
            "Block all or permit read-only operations during a code analysis. This option is recommended for large binaries as the initial analysis will terminate faster."
         )
      );
      int var4 = Licensing.isDebugBuild() ? 1 : 2;
      var0.addDefinition(
         "DebugInformationUsagePolicy",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("None"), S.L("Never use debug information"))
            .addEntry(1, S.L("Internal"), S.L("Use internal debug information only"))
            .addEntry(2, S.L("All available"), S.L("Use all available (internal and external) debug information"))
            .setDefault(var4)
            .build(),
         S.L("Apply and use debug information to improve analysis accuracy (on a best-effort basis)")
      );
      var0.addDefinition(
         "DebugInformationRetrievalPolicy",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("Never"), S.L("Do not attempt"))
            .addEntry(1, S.L("Local only"), S.L("Retrieve locally stored external symbols"))
            .addDefaultEntry(2, S.L("Local and Network"), S.L("Attempt to retrieve locally and network-stored symbols"))
            .build(),
         S.L(
            "Policy regarding the retrieval of externally-stored debugging and symbolic information of code objects. Locations are architecture- and code object- dependent and may be customized via environment variables. Refer to the manual for additional documentation."
         )
      );
      var0.addDefinition("UseTypeLibraries", PropertyTypeBoolean.create(true), S.L("Use native type libraries (e.g. win32, libc, etc.)"), 2);
      var0.addDefinition(
         "SignaturePackagesLoading",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("Disabled"), S.L("No signatures packages can be loaded, or created"))
            .addEntry(1, S.L("Manual"), S.L("Signature packages have to be manually loaded"))
            .addDefaultEntry(
               2,
               S.L("Automatic"),
               S.L("JEB's default packages are automatically loaded (based on architecture and compiler) and additional packages can be manually loaded")
            )
            .build(),
         S.L(
            "Select how signature packages are loaded. Two types of packages exist: JEB default packages for common libraries, and user-made packages (refer to manual for additional information)."
         ),
         10
      );
      var0.addDefinition(
         "LoadTypeLibrariesCodelessSigs",
         PropertyTypeBoolean.create(true),
         S.L("When applying codeless signatures, automatically load corresponding type libraries."),
         2
      );
      var0.addDefinition(
         "CompilerIdentification",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Automatic"), null)
            .addEntry(1, S.L("Unknown compiler"), null)
            .addEntry(2, S.L("Generic Linux compiler"), null)
            .addEntry(3, S.L("Android ART"), null)
            .addEntry(4, S.L("Android NDK"), null)
            .addEntry(5, S.L("Generic Windows compiler"), null)
            .addEntry(6, S.L("Microsoft Visual C++"), null)
            .build(),
         null
      );
      var0.addDefinition(
         "TailCallAnalysisStyle",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(-1, S.L("Automatic"), null)
            .addEntry(iL.Av.pC.ordinal(), S.L("Disabled"), null)
            .addEntry(iL.Av.A.ordinal(), S.L("Safe heuristics only"), null)
            .addEntry(iL.Av.kS.ordinal(), S.L("Aggressive heuristics"), null)
            .build(),
         S.L("Search for possible tail calls optimized as branches, and build proper routines.")
      );
      var0.addDefinition(
         "SwitchAnalysisStyle",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(-1, S.L("Automatic"), null)
            .addEntry(wn.Av.pC.ordinal(), S.L("Disabled"), null)
            .addEntry(wn.Av.A.ordinal(), S.L("Safe heuristics only"), null)
            .addEntry(wn.Av.kS.ordinal(), S.L("Aggressive heuristics"), null)
            .build(),
         S.L("Search for possible switch statements at assembly level.")
      );
      var0.addDefinition(
         "ApplyAndroidNativePrototypes",
         PropertyTypeBoolean.create(true),
         S.L("Generate and apply prototypes for native methods which are the implementations of Java Native abstract methods.")
      );
      var0.addDefinition(
         "CreatePackagesFromRoutineNames",
         PropertyTypeBoolean.create(true),
         S.L(
            "Automatically create packages from native method names; for example 'std::foo::bar' method will be moved to package 'std::foo' and renamed 'bar'."
         )
      );
      var0.addInternalDefinition("PreferHexAddresses", PropertyTypeBoolean.create(true));
      axz.pC(var0);
   }

   @Override
   public boolean acceptAnyInputBytes() {
      return this.pC.canBeProcessedOutsideCodeObject();
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var2 instanceof ICodeObjectUnit) {
         ByteBuffer var5 = var1.getHeader();
         if (var2 instanceof IELFUnit && ((IELFUnit)var2).getHeader() != null) {
            if (var5.limit() >= 20 && ELFIdentifier.pC(var1)) {
               int var8 = ((IELFUnit)var2).getHeader().getMachine();
               boolean var9 = ((IELFUnit)var2).isELF64();
               if (this.pC().canProcessELF(var8, var9)) {
                  return true;
               }
            }
         } else if (var2 instanceof IPECOFFUnit && ((IPECOFFUnit)var2).getCOFFHeader() != null && PEIdentifier.pC(var1)) {
            int var6 = ((IPECOFFUnit)var2).getCOFFHeader().getMachine();
            boolean var7 = ((IPECOFFUnit)var2).isPE64();
            if (this.pC().canProcessPE(var6, var7)) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      C var6 = new C(var1, this.getFormatType(), var2, var3, var4, this.pdm);
      var6.setMemory(this.pC.getMemory(var4));
      var6.setProcessor(this.pC.getProcessor(var4));
      var6.setCallingConvention(this.pC.getCallingConvention(var4));
      var6.setCodeFormatter(this.pC.getCodeFormatter());
      var6.setAnalyzerExtension(this.pC.getAnalyzerExtension());
      var6.getContributions().addAll(this.pC.createContributions(var6));
      return var6;
   }

   @Override
   public String toString() {
      return "Native code identifier for plugin: " + this.pC;
   }
}
