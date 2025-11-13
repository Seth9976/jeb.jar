package com.pnfsoftware.jeb.client.telemetry;

import java.util.List;

public interface ITelemetryEndpoint {
   boolean canDump();

   boolean dump(List var1);
}
