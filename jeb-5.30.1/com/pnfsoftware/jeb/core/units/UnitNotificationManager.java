package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.collect.SynchronizedLinkedMap;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.util.Collection;
import java.util.List;

@Ser
@SerVersion(1)
public class UnitNotificationManager implements IUnitNotificationManager {
   @SerId(1)
   private List notifications;
   @SerId(value = 2, version = 1)
   private SynchronizedLinkedMap map = new SynchronizedLinkedMap();

   @SerCustomInitPostGraph
   private void init() {
      if (this.notifications != null) {
         this.map = new SynchronizedLinkedMap();

         for (IUnitNotification var2 : this.notifications) {
            this.addNotification(new UnitNotification(var2));
         }

         this.notifications = null;
      }
   }

   @Override
   public List getNotifications() {
      return this.map.copyOfValues();
   }

   @Override
   public int getNotificationCount() {
      return this.map.size();
   }

   @Override
   public void addNotification(IUnitNotification var1) {
      this.map.put(var1.getKey(), var1);
   }

   @Override
   public void addNotifications(Collection var1) {
      for (IUnitNotification var3 : var1) {
         this.addNotification(var3);
      }
   }

   @Override
   public boolean removeNotification(String var1) {
      return this.map.remove(var1) != null;
   }

   @Override
   public IUnitNotification getNotification(String var1) {
      return (IUnitNotification)this.map.get(var1);
   }
}
