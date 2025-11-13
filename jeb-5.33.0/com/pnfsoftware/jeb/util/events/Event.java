package com.pnfsoftware.jeb.util.events;

import java.util.ArrayList;
import java.util.List;

public class Event implements IEvent {
   int type;
   Object data;
   EventSource source;
   long timestamp = System.currentTimeMillis();
   boolean stopPropagation;
   List notifiedListeners = new ArrayList();
   List notifiedParents = new ArrayList();

   public Event() {
   }

   public Event(int var1) {
      this.type = var1;
   }

   public Event(int var1, Object var2) {
      this.type = var1;
      this.data = var2;
   }

   public Event(int var1, Object var2, EventSource var3) {
      this.type = var1;
      this.data = var2;
      this.source = var3;
   }

   @Override
   public IEventSource getSource() {
      return this.source;
   }

   public Integer getType() {
      return this.type;
   }

   @Override
   public Object getData() {
      return this.data;
   }

   @Override
   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public boolean shouldStopPropagation() {
      return this.stopPropagation;
   }
}
