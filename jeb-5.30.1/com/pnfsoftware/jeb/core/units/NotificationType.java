package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum NotificationType {
   UNSUPPORTED_FEATURE(10, S.L("Unsupported feature")),
   DEPRECATED_FEATURE(20, S.L("Deprecated feature")),
   CORRUPTION(30, S.L("Corruption")),
   AREA_OF_INTEREST(50, S.L("Area of interest")),
   INFO(50, S.L("Informational")),
   WARNING(60, S.L("Generic warning")),
   ERROR(65, S.L("Generic error")),
   POTENTIALLY_HARMFUL(70, S.L("Potentially harmful")),
   MALICIOUS(100, S.L("Malicious"));

   private final int level;
   private final String description;

   private NotificationType(int var3, String var4) {
      this.level = var3;
      this.description = var4;
   }

   public int getLevel() {
      return this.level;
   }

   public String getDescription() {
      return this.description;
   }

   @Override
   public String toString() {
      return this.description;
   }
}
