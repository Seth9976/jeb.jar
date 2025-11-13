package com.pnfsoftware.jeb.core.units.code.debug;

public interface IDebuggerProcessInformation {
   int FLAG_DEBUGGABLE = 1;

   long getId();

   String getName();

   int getFlags();
}
