package com.pnfsoftware.jeb.util.events;

import java.util.List;

public interface IEventSource {
   void setParentSource(IEventSource var1);

   IEventSource getParentSource();

   int countListeners();

   List getListeners();

   void addListener(IEventListener var1);

   void insertListener(int var1, IEventListener var2);

   void removeListener(IEventListener var1);

   void notifyListeners(IEvent var1);
}
