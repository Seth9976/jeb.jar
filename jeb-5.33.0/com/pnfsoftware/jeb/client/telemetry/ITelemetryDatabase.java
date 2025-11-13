package com.pnfsoftware.jeb.client.telemetry;

import java.util.Map;

public interface ITelemetryDatabase {
   void close() throws TelemetryException;

   boolean record(String var1);

   boolean record(String var1, String var2, String var3);

   boolean record(String var1, String var2, String var3, String var4, String var5);

   boolean record(String var1, Map var2);
}
