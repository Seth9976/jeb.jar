package com.pnfsoftware.jeb.core.units.code.debug;

import java.util.List;

public interface IDebuggerMachineInformation {
   int FLAG_AVAILABLE = 1;

   int getFlags();

   String getName();

   String getLocation();

   String getInformation();

   List getProcesses();
}
