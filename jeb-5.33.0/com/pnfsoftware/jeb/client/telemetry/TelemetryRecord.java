package com.pnfsoftware.jeb.client.telemetry;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collections;
import java.util.Map;

public class TelemetryRecord {
   int timestamp;
   String version;
   String eventname;
   Map properties;

   public TelemetryRecord(int var1, String var2, String var3, Map var4) {
      this.timestamp = var1;
      this.version = var2;
      this.eventname = var3;
      if (var4 == null) {
         var4 = Collections.emptyMap();
      }

      this.properties = var4;
   }

   public int getTimestamp() {
      return this.timestamp;
   }

   public String getVersion() {
      return this.version;
   }

   public String getEventname() {
      return this.eventname;
   }

   public Map getProperties() {
      return this.properties;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%d,%s,%s,%s", this.timestamp, Strings.urlencodeUTF8(this.version), Strings.urlencodeUTF8(this.eventname), Strings.encodeMap(this.properties)
      );
   }
}
