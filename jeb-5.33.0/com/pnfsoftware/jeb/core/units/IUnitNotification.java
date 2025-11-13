package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IUnitNotification {
   NotificationType getType();

   String getDescription();

   String getAddress();

   String getKey();

   long getTimestampMs();
}
