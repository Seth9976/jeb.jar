package com.pnfsoftware.jeb.core.units.code.debug;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class DebuggerException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public DebuggerException() {
   }

   public DebuggerException(String var1) {
      super(var1);
   }

   public DebuggerException(Throwable var1) {
      super(var1);
   }

   public DebuggerException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
