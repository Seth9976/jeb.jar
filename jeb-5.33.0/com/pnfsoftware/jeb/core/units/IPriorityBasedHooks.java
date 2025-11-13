package com.pnfsoftware.jeb.core.units;

public interface IPriorityBasedHooks {
   default int getPriority() {
      return 0;
   }
}
