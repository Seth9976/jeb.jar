package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerSetupInformation;
import java.util.List;

public interface IDebuggerUnit extends IInteractiveUnit {
   IDebuggerTargetEnumerator getTargetEnumerator();

   boolean canPerformOperation(DebuggerOperationType var1);

   boolean setSuspendPolicy(DebuggerEventType var1, DebuggerSuspendPolicy var2);

   DebuggerSuspendPolicy getSuspendPolicy(DebuggerEventType var1);

   List getModules();

   List getPotentialDebuggees();

   boolean registerDebuggee(ICodeUnit var1);

   boolean unregisterDebuggee(ICodeUnit var1);

   IUnit getTargetApplication();

   boolean isAttached();

   boolean attach(DebuggerSetupInformation var1);

   boolean restart();

   boolean detach();

   IDebuggerTargetInformation getTargetInformation();

   boolean run();

   boolean pause();

   boolean isPaused();

   boolean terminate();

   List getThreads();

   IDebuggerThread getThreadById(long var1);

   boolean setDefaultThread(long var1);

   IDebuggerThread getDefaultThread();

   boolean hasDefaultThread();

   IDebuggerBreakpoint setBreakpoint(String var1, ICodeUnit var2, int var3);

   default IDebuggerBreakpoint setBreakpoint(String var1, ICodeUnit var2) {
      return this.setBreakpoint(var1, var2, 0);
   }

   IDebuggerBreakpoint getBreakpoint(String var1, ICodeUnit var2);

   List getBreakpoints();

   boolean clearBreakpoint(IDebuggerBreakpoint var1);

   boolean clearBreakpoints();

   IDebuggerBreakpoint setBreakpoint(long var1);

   IDebuggerBreakpoint setBreakpoint(long var1, int var3);

   IDebuggerBreakpoint getBreakpoint(long var1);

   IDebuggerVirtualMemory getMemory();

   int readMemory(long var1, int var3, byte[] var4, int var5);

   int writeMemory(long var1, int var3, byte[] var4, int var5);

   UnitAddress convertToUnitAddress(String var1);

   long convertSymbolicAddressToMemoryToAddress(String var1, ICodeUnit var2);

   IProcessor getProcessor();
}
