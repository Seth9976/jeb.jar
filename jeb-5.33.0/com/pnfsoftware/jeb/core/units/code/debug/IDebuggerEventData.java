package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.UnitAddress;

public interface IDebuggerEventData {
   DebuggerEventType getType();

   long getThreadId();

   String getAddress();

   UnitAddress getUnitAddress();

   ITypedValue getReturnValue();

   byte[] getOutput();
}
