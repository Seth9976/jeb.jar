package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IUnitNotificationManager {
   List getNotifications();

   int getNotificationCount();

   void addNotification(IUnitNotification var1);

   void addNotifications(Collection var1);

   boolean removeNotification(String var1);

   IUnitNotification getNotification(String var1);
}
