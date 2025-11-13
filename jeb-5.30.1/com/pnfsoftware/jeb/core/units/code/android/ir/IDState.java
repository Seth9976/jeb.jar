package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.INativeLibrary;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDState {
   int DEFAULT_MAX_ITERCOUNT = 100;
   int DEFAULT_MAX_DURATION = 1000;
   int DEFAULT_MAX_INVOCATION_DEPTH = 50;
   int SUBEXEC_ALLOW_INTERNAL = 1;
   int SUBEXEC_ALLOW_EXTERNAL = 2;
   int SUBEXEC_DENY_ALL = 0;
   int SUBEXEC_ALLOW_ALL = 255;
   int FIELDACCESS_ALLOW_READ_EXTERNAL_INSTANCE = 1;
   int FIELDACCESS_ALLOW_WRITE_EXTERNAL_INSTANCE = 2;
   int FIELDACCESS_ALLOW_READ_INTERNAL_INSTANCE = 4;
   int FIELDACCESS_ALLOW_WRITE_INTERNAL_INSTANCE = 8;
   int FIELDACCESS_ALLOW_READ_EXTERNAL_STATIC = 16;
   int FIELDACCESS_ALLOW_WRITE_EXTERNAL_STATIC = 32;
   int FIELDACCESS_ALLOW_READ_INTERNAL_STATIC = 64;
   int FIELDACCESS_ALLOW_WRITE_INTERNAL_STATIC = 128;
   int FIELDACCESS_DENY_ALL = 0;
   int FIELDACCESS_ALLOW_ALL = 255;

   void dispose();

   IDGlobalContext getGlobalContext();

   IDexDecompilerUnit getDecompiler();

   IDexUnit getDex();

   IApkUnit getApk();

   IEmulatedAndroid getEmulatedEnvironment();

   boolean canRun();

   int getMaxIterationCount();

   int setMaxIterationCount(int var1);

   int getCurrentIterationCount();

   int getIterationCountLeft();

   long getMaxDuration();

   long setMaxDuration(long var1);

   long getCurrentDuration();

   long getTimeLeft();

   Watchdog getWatchdog();

   Watchdog setWatchdog(Watchdog var1);

   IJLSTypeAdapter getTypeAdapter();

   int setSubRoutineInvocationPolicy(int var1);

   int getSubRoutineInvocationPolicy();

   int setFieldAccessPolicy(int var1);

   int getFieldAccessPolicy();

   boolean setStrictClassInit(boolean var1);

   boolean isStrictClassInit();

   int setMaxInvocationDepth(int var1);

   int getMaxInvocationDepth();

   void enableEmulator(boolean var1);

   boolean isEmulatorEnabled();

   int registerEmulatorHooks(IDEmulatorHooks var1);

   boolean unregisterEmulatorHooks(int var1);

   void registerExecutionHelper(IDMethodExecutionHelper var1);

   boolean unregisterExecutionHelper(String var1);

   Collection getExecutionHelpers();

   DEmuExternalPolicy setExternalPolicy(DEmuExternalPolicy var1);

   DEmuExternalPolicy getExternalPolicy();

   void enableExceptionHandling(boolean var1);

   boolean setExceptionHandlingEnabled(boolean var1);

   boolean isExceptionHandlingEnabled();

   boolean isRequireNonNullObjectForNonStaticInvoke();

   boolean setRequireNonNullObjectForNonStaticInvoke(boolean var1);

   boolean setPerformDirectUnreflection(boolean var1);

   boolean isPerformDirectUnreflection();

   boolean enableSandbox(boolean var1);

   boolean isSandboxEnabled();

   URLClassLoader getSandboxClassLoader();

   void addClassfilesToSandbox(File var1) throws IOException;

   int registerSandboxHooks(IDSandboxHooks var1);

   boolean unregisterSandboxHooks(int var1);

   File setSandboxDroppedFilesCollection(String var1, boolean var2) throws IOException;

   File getSandboxDropFolder();

   void enableNativeCodeEmulator(boolean var1);

   boolean isNativeCodeEmulatorEnabled();

   boolean isLazyJNIOnLoadExec();

   boolean setLazyJNIOnLoadExec(boolean var1);

   int registerNativeEmulatorHooks(IEEmulatorHooks var1);

   boolean unregisterNativeEmulatorHooks(int var1);

   int registerNativeEmulatorMemoryHooks(IEStateHooks var1);

   boolean unregisterNativeEmulatorMemoryHooks(int var1);

   IDImm getStaticField(String var1) throws DexDecEvaluationException;

   void setStaticField(String var1, IDImm var2) throws DexDecEvaluationException;

   IDImm getInstanceField(String var1, IDImm var2) throws DexDecEvaluationException;

   void setInstanceField(String var1, IDImm var2, IDImm var3) throws DexDecEvaluationException;

   Class loadClass(String var1) throws DexDecEvaluationException;

   Class loadClass(String var1, boolean var2) throws DexDecEvaluationException;

   String getStringObject(IDImm var1) throws DexDecEvaluationException;

   IDImm createArray(IJavaType var1, int var2, List var3) throws DexDecEvaluationException;

   IDImm cloneArray(IDImm var1) throws DexDecEvaluationException;

   Object getArrayObject(IDImm var1) throws DexDecEvaluationException;

   int getArrayObjectLength(IDImm var1) throws DexDecEvaluationException;

   IDImm getArrayElement(IDImm var1, int var2) throws DexDecEvaluationException;

   void setArrayElement(IDImm var1, int var2, IDImm var3) throws DexDecEvaluationException;

   IDImm getClassReference(String var1) throws DexDecEvaluationException;

   boolean isInstanceOf(IDImm var1, IDImm var2) throws DexDecEvaluationException;

   IDImm registerObject(Object var1);

   Object getObject(IDImm var1) throws DexDecEvaluationException;

   Object getObject(IDImm var1, boolean var2) throws DexDecEvaluationException;

   Object releaseObject(IDImm var1) throws DexDecEvaluationException;

   Object getObject(int var1) throws DexDecEvaluationException;

   int getObjectClassId(int var1) throws DexDecEvaluationException;

   IDEmuClass getObjectClass(int var1) throws DexDecEvaluationException;

   IDEmuContext pushContext(String var1);

   IDEmuContext deleteTopContext();

   IDEmuFrame pushFrame(String var1);

   IDEmuFrame deleteTopFrame();

   Collection getContexts();

   int getCountOfContexts();

   IDEmuContext getCurrentContext();

   IDEmuFrame getCurrentFrame();

   void setVariable(int var1, IDImm var2) throws DexDecEvaluationException;

   IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException;

   IDImm getVariable(int var1) throws DexDecEvaluationException;

   boolean hasVariable(int var1) throws DexDecEvaluationException;

   boolean deleteVariable(int var1) throws DexDecEvaluationException;

   void setPC(int var1);

   int getPC();

   IDImm createNewInstance(String var1, Collection var2) throws DexDecEvaluationException;

   IDImm createNewInstance(String var1) throws DexDecEvaluationException;

   IDImm invokeMethod(String var1, Collection var2, DInvokeType var3) throws DexDecEvaluationException;

   IDImm invokeMethod(DInvokeType var1, String var2, IDExpression... var3) throws DexDecEvaluationException;

   IDImm executeDexMethod(IDexMethod var1, List var2) throws DexDecEvaluationException;

   IDImm execute(DExecutionParameters var1) throws DexDecEvaluationException;

   List getNativeLibraries() throws DexDecEvaluationException;

   INativeLibrary loadNativeLibrary(IELFUnit var1) throws DexDecEvaluationException;

   INativeLibrary loadNativeLibrary(String var1) throws DexDecEvaluationException;

   INativeLibrary loadNativeLibrary(String var1, boolean var2) throws DexDecEvaluationException;

   EEmulator getNativeEmulator();

   Map getRegisteredNatives();

   void setData(String var1, Object var2);

   Object getData(String var1);

   IDState shallowCopy();
}
