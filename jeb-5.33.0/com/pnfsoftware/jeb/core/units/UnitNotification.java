package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class UnitNotification implements IUnitNotification {
   @SerId(1)
   private NotificationType type;
   @SerId(2)
   private String description;
   @SerId(3)
   private String address;
   @SerId(4)
   private String key;
   @SerId(5)
   private long timestampMs;

   private UnitNotification(NotificationType var1, String var2, String var3, long var4, String var6) {
      this.type = var1;
      this.description = var2;
      this.address = var3;
      this.timestampMs = var4;
      this.key = Strings.safe(var6, Strings.randomUniqueId());
   }

   public UnitNotification(NotificationType var1, String var2, String var3, String var4) {
      this(var1, var2, var3, System.currentTimeMillis(), var4);
   }

   public UnitNotification(NotificationType var1, String var2, String var3) {
      this(var1, var2, var3, null);
   }

   public UnitNotification(NotificationType var1, String var2) {
      this(var1, var2, null);
   }

   public UnitNotification(IUnitNotification var1) {
      this(var1.getType(), var1.getDescription(), var1.getAddress(), var1.getTimestampMs(), var1.getKey());
   }

   @Override
   public NotificationType getType() {
      return this.type;
   }

   @Override
   public String getDescription() {
      return this.description;
   }

   @Override
   public String getAddress() {
      return this.address;
   }

   @Override
   public String getKey() {
      return this.key;
   }

   @Override
   public long getTimestampMs() {
      return this.timestampMs;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      if (this.getDescription() != null) {
         var1.append(": ").append(this.getDescription());
      }

      if (this.getAddress() != null) {
         var1.append(" @ ").append(this.getAddress());
      }

      return var1.toString();
   }
}
