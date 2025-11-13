package com.pnfsoftware.jeb.core.units.code.debug;

import java.util.List;

public interface IDebuggerThreadStackFrame {
   long getId();

   String getAddress();

   List getVariables();

   List getVariables(boolean var1);

   boolean setVariable(IDebuggerVariable var1);

   IDebuggerVariable getInternalParameter(int var1, String var2);
}
