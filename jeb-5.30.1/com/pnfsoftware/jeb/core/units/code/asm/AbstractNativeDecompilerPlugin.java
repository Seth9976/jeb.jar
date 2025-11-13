package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ISourceCustomizer;

public abstract class AbstractNativeDecompilerPlugin extends AbstractNativePlugin implements INativeDecompilerPlugin {
   public static final String propnameMethodDecompilationTimeout = "MethodDecompilationTimeout";
   public static final String propnameMemoryResolutionPolicy = "MemoryResolutionPolicy";
   public static final String propnameReconversionMaxCount = "ReconversionMaxCount";
   public static final String propnameIROptimizerMaxRunCount = "IROptimizerMaxRunCount";
   public static final String propnameEnableUnsafeOptimizers = "EnableUnsafeOptimizers";
   public static final String propnameDecryptorSupport = "DecryptorSupport";
   public static final String propnameEnableDeobfuscators = "EnableDeobfuscators";
   public static final String propnameASTOptimizerMaxRunCount = "ASTOptimizerMaxRunCount";
   public static final String propnameStructurerUseVersion = "StructurerUseVersion";
   public static final String propnameUseFriendlyVariableNames = "UseFriendlyVariableNames";
   public static final String propnameUseWellKnownLiterals = "UseWellKnownLiterals";
   public static final String propnameNextDecompKeepIR = "NextDecompKeepIR";
   public static final String propnameEnableExternalPlugins = "EnableExternalPlugins";
   public static final String propnameListOfDisabledExternalPlugins = "ListOfDisabledExternalPlugins";

   public AbstractNativeDecompilerPlugin(String var1, double var2) {
      super(var1, var2);
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return null;
   }

   @Override
   public IGlobalAnalyzer getGlobalAnalyzer(INativeDecompilerUnit var1) {
      return null;
   }

   @Override
   public ISourceCustomizer getSourceCustomizer(INativeDecompilerUnit var1) {
      return null;
   }
}
