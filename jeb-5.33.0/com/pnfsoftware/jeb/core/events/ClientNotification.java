package com.pnfsoftware.jeb.core.events;

import com.pnfsoftware.jeb.util.format.Strings;

public class ClientNotification {
   private String message;
   private ClientNotificationLevel level;

   public ClientNotification(String var1) {
      this(var1, ClientNotificationLevel.INFO);
   }

   public ClientNotification(String var1, ClientNotificationLevel var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Notification level is null");
      } else {
         this.level = var2;
         this.message = Strings.safe(var1);
      }
   }

   public String getMessage() {
      return this.message;
   }

   public ClientNotificationLevel getLevel() {
      return this.level;
   }
}
