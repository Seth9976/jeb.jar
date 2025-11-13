package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class CSimulationException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public CSimulationException() {
   }

   public CSimulationException(String var1) {
      super(var1);
   }

   public CSimulationException(Throwable var1) {
      super(var1);
   }

   public CSimulationException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
