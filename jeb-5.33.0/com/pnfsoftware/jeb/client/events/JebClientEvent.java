package com.pnfsoftware.jeb.client.events;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.format.Strings;

public class JebClientEvent implements IEvent {
   JC type;
   Object data;
   IEventSource source;
   long timestamp = System.currentTimeMillis();
   boolean stopPropagation;

   public JebClientEvent(JC var1) {
      this.type = var1;
   }

   public JebClientEvent(JC var1, Object var2) {
      this.type = var1;
      this.data = var2;
   }

   public JebClientEvent(JC var1, Object var2, IEventSource var3) {
      this.type = var1;
      this.data = var2;
      this.source = var3;
   }

   public JC getType() {
      return this.type;
   }

   @Override
   public Object getData() {
      return this.data;
   }

   @Override
   public IEventSource getSource() {
      return this.source;
   }

   @Override
   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public boolean shouldStopPropagation() {
      return this.stopPropagation;
   }

   @Override
   public String toString() {
      return Strings.ff(S.L("type=%s,data=%s,from=%s"), this.type, this.data, this.source);
   }
}
