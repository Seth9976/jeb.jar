package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.units.UnitAddress;

public interface IDebuggerBreakpoint {
   int BREAK_DEFAULT = 0;
   int BREAK_ON_READ = 1;
   int BREAK_ON_WRITE = 2;

   String getAddress();

   UnitAddress getUnitAddress();

   int getFlags();

   boolean setEnabled(boolean var1);

   boolean isEnabled();
}
