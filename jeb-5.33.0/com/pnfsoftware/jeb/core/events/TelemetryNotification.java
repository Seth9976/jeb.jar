package com.pnfsoftware.jeb.core.events;

import java.util.HashMap;
import java.util.Map;

public class TelemetryNotification extends ClientNotification {
   private Map properties;

   public TelemetryNotification(String var1, Map var2) {
      super(var1);
      this.properties = (Map)(var2 == null ? new HashMap() : var2);
   }

   public String getEventName() {
      return this.getMessage();
   }

   public Map getProperties() {
      return this.properties;
   }
}
