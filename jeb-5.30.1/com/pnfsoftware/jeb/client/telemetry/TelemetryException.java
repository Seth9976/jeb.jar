package com.pnfsoftware.jeb.client.telemetry;

import com.pnfsoftware.jeb.core.exceptions.JebException;

public class TelemetryException extends JebException {
   private static final long serialVersionUID = 1L;

   public TelemetryException() {
   }

   public TelemetryException(String var1) {
      super(var1);
   }

   public TelemetryException(Throwable var1) {
      super(var1);
   }

   public TelemetryException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
