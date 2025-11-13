package com.pnfsoftware.jeb.util.events;

class EventSource$1 implements IEventListener {
   EventSource$1(IEventSource var1) {
      this.val$proxy = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      this.val$proxy.notifyListeners(var1);
   }
}
