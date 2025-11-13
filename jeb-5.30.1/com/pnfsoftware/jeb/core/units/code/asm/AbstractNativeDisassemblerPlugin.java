package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNativeDisassemblerPlugin extends AbstractNativePlugin implements INativeDisassemblerPlugin {
   public static final String propnameEndianness = "Endianness";
   public static final String propnameImageBase = "ImageBase";
   public static final String propnameRelocationBaseForZeroBasedRelocatableObjects = "RelocationBaseForZeroBasedRelocatableObjects";
   public static final String propnameCallingConvention = "CallingConvention";
   public static final String propnameDelayAnalysis = "DelayAnalysis";
   public static final String propnameAllowAdvancedAnalysis = "AdvancedAnalysis";
   public static final String propnameForceSynchronousAnalysis = "ForceSynchronousAnalysis";
   public static final String propnameAnalysisStyle = "AnalysisStyle";
   public static final String propnamePerformClassRecovery = "PerformClassRecovery";
   public static final String propnamePerformRttiRecovery = "PerformRttiRecovery";
   public static final String propnamePerformGlobalAnalysis = "PerformGlobalAnalysis";
   public static final String propnameDebugInformationUsagePolicy = "DebugInformationUsagePolicy";
   public static final String propnameDebugInformationRetrievalPolicy = "DebugInformationRetrievalPolicy";
   public static final String propnameUseTypeLibraries = "UseTypeLibraries";
   public static final String propnameLoadTypelibrariesCodelessSigs = "LoadTypeLibrariesCodelessSigs";
   public static final String propnameSignaturePackagesLoading = "SignaturePackagesLoading";
   public static final String propnameCompilerIdentification = "CompilerIdentification";
   public static final String propnameTailCallAnalysisStyle = "TailCallAnalysisStyle";
   public static final String propnameSwitchAnalysisStyle = "SwitchAnalysisStyle";
   public static final String propnamePreferHexAddresses = "PreferHexAddresses";
   public static final String propnamePerformFakeRoutineCallAnalysis = "PerformFakeRoutineCallAnalysis";
   public static final String propnameApplyAndroidNativePrototypes = "ApplyAndroidNativePrototypes";
   public static final String propnameCreatePackagesFromRoutineNames = "CreatePackagesFromRoutineNames";

   public AbstractNativeDisassemblerPlugin(String var1, double var2) {
      super(var1, var2);
   }

   @Override
   public boolean canBeProcessedOutsideCodeObject() {
      return true;
   }

   @Override
   public boolean canProcessELF(int var1, boolean var2) {
      return false;
   }

   @Override
   public boolean canProcessPE(int var1, boolean var2) {
      return false;
   }

   @Override
   public List getProcessorTypes() {
      return Collections.emptyList();
   }

   @Override
   public ICallingConvention getCallingConvention(IUnitCreator var1) {
      return null;
   }

   @Override
   public String toString() {
      return "Disassembler: " + this.type;
   }
}
