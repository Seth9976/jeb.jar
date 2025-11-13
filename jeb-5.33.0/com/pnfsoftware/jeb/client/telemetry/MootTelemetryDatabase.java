package com.pnfsoftware.jeb.client.telemetry;

import java.util.Map;

public class MootTelemetryDatabase implements ITelemetryDatabase {
   @Override
   public void close() throws TelemetryException {
   }

   @Override
   public boolean record(String var1) {
      return false;
   }

   @Override
   public boolean record(String var1, Map var2) {
      return false;
   }

   @Override
   public boolean record(String var1, String var2, String var3) {
      return false;
   }

   @Override
   public boolean record(String var1, String var2, String var3, String var4, String var5) {
      return false;
   }
}
