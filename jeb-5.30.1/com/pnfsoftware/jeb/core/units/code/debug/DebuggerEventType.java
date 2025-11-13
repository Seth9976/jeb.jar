package com.pnfsoftware.jeb.core.units.code.debug;

public enum DebuggerEventType {
   SUSPENDED,
   BREAKPOINT,
   BREAKPOINT_FUNCTION_EXIT,
   EXCEPTION,
   FUNCTION_ENTRY,
   FUNCTION_EXIT,
   CODE_LOAD,
   CODE_UNLOAD,
   THREAD_START,
   THREAD_STOP,
   SIGNAL,
   OUTPUT;
}
