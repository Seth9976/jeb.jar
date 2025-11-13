package com.pnfsoftware.jeb.util.events;

public interface IEvent {
   IEventSource getSource();

   Object getType();

   Object getData();

   long getTimestamp();

   boolean shouldStopPropagation();
}
