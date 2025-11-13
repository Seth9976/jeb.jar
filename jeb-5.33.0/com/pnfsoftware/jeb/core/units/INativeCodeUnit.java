package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.IFieldManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.INativeDisassemblyDocument;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackageManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.SortedMap;

@Ser
public interface INativeCodeUnit extends ICodeUnit, INativeContext, IMethodManager {
   String externalSymbolsPrefix = "extern";
   String importPtrPrefix = "ptr_";
   String trampolinePrefix = "→";
   String targetPrefix = "*";

   @Override
   IUnitLock getLock();

   INativeItem getItemObject(long var1);

   String getAddressFromCodeCoordinates(ICodeCoordinates var1, AddressConversionPrecision var2);

   @Override
   List getStrings();

   INativeStringItem getStringByIndex(int var1);

   @Override
   List getPackages();

   @Override
   List getTypes();

   @Override
   List getClasses();

   @Override
   List getFields();

   @Override
   List getMethods();

   INativeClassItem getClass(String var1);

   INativeFieldItem getField(String var1);

   INativeMethodItem getMethod(String var1);

   INativeClassItem getClassByIndex(int var1);

   INativeFieldItem getFieldByIndex(int var1);

   INativeMethodItem getMethodByIndex(int var1);

   INativeDisassemblyDocument getDisassemblyDocument();

   ICodeObjectUnit getCodeObjectContainer();

   List getInternalMethods();

   List getInternalMethodsLeafFirst();

   List getInternalMethodsSizeFirst();

   INativeMethodItem getInternalMethod(long var1);

   INativeMethodItem getInternalMethod(long var1, boolean var3);

   List getInternalMethods(long var1);

   void setProcessor(IProcessor var1);

   void setCallingConvention(ICallingConvention var1);

   @Override
   IProcessor getProcessor();

   String getProcessorName();

   Endianness getEndianness();

   void setMemory(IVirtualMemory var1);

   @Override
   IVirtualMemory getMemory();

   void setSubsystemType(SubsystemType var1);

   void setCompilerType(CompilerType var1);

   void setCodeFormatter(GenericCodeFormatter var1);

   GenericCodeFormatter getCodeFormatter();

   INativeCodeAnalyzerExtension getAnalyzerExtension();

   void setAnalyzerExtension(INativeCodeAnalyzerExtension var1);

   INativeCodeModel getCodeModel();

   INativeCodeAnalyzer getCodeAnalyzer();

   INativeCodeAnalyzerExtensionsManager getCodeAnalyzerExtensionsManager();

   @Override
   boolean process();

   boolean isInitialAnalysisDone();

   boolean performInitialAnalysis();

   boolean performInitialAnalysis(Boolean var1);

   boolean performAnalysis(boolean var1, Boolean var2, Runnable var3);

   boolean isAnalysisCompleted();

   long getCanonicalMemoryAddress(String var1);

   long getCanonicalMemoryAddress(String var1, AddressConversionPrecision var2);

   String getSymbolicStringAddress(long var1);

   String getSymbolicStringAddress(long var1, int var3);

   void setVirtualImageBase(long var1);

   void setPhysicalImageBase(long var1);

   long getPhysicalImageDelta();

   long getEntryPointAddress();

   long getHighLevelEntryPointAddress();

   NativeSignatureDBManager getSignatureManager();

   @Override
   ITypeManager getTypeManager();

   IPackageManager getPackageManager();

   IClassManager getClassManager();

   IFieldManager getFieldManager();

   IMethodManager getMethodManager();

   TypeLibraryService getTypeLibraryService();

   boolean setRoutinePrototype(INativeMethodItem var1, String var2);

   boolean setRoutineSignature(INativeMethodItem var1, String var2, boolean var3);

   INativeType getDataTypeAt(long var1);

   boolean setDataTypeAt(long var1, INativeType var3);

   boolean setDataAt(long var1, INativeType var3, String var4);

   boolean setDataAt(long var1, INativeType var3, String var4, boolean var5);

   boolean setStringAt(long var1, long var3, StringEncoding var5, int var6, int var7);

   boolean setStringAt(long var1, long var3, StringEncoding var5, int var6, int var7, boolean var8);

   boolean setCodeAt(long var1, int var3, boolean var4);

   boolean setRoutineReferenceAt(long var1, INativeMethodItem var3);

   boolean setRoutineAt(long var1);

   boolean setRoutineAt(long var1, int var3);

   boolean setRoutineAt(long var1, int var3, int var4);

   @Override
   INativeContinuousItem getNativeItemAt(long var1);

   @Override
   INativeContinuousItem getNativeItemOver(long var1);

   SortedMap getNativeItemsOver(long var1, int var3);

   boolean undefineItem(long var1);

   NativeCommentManager getCommentManager();

   boolean setInlineComment(long var1, String var3);

   String getInlineComment(long var1);

   INativeDecompilerUnit getDecompiler();
}
