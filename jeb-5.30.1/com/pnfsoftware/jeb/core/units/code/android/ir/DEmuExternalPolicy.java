package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import java.util.HashSet;
import java.util.Set;

public class DEmuExternalPolicy {
   public static final int RESTRICT_RANDOM = 1;
   public static final int RESTRICT_TIME = 2;
   public static final int RESTRICT_ENV = 4;
   static Set random_classes = new HashSet();
   static Set random_methods = new HashSet();
   static Set random_fields = new HashSet();
   static Set time_classes = new HashSet();
   static Set time_methods = new HashSet();
   static Set time_fields = new HashSet();
   static Set env_classes = new HashSet();
   static Set env_methods = new HashSet();
   static Set env_fields = new HashSet();
   private boolean defaultAllow = true;
   private int flags;
   private Set restrictedClasses = new HashSet();
   private Set restrictedMethods = new HashSet();
   private Set restrictedFields = new HashSet();
   private Set whitelistedClasses = new HashSet();
   private Set whitelistedMethods = new HashSet();
   private Set whitelistedFields = new HashSet();
   private Set blacklistedClasses = new HashSet();
   private Set blacklistedMethods = new HashSet();
   private Set blacklistedFields = new HashSet();

   public DEmuExternalPolicy() {
      this(true, true, true);
   }

   public DEmuExternalPolicy(boolean var1, boolean var2, boolean var3) {
      if (var1) {
         this.flags |= 1;
      }

      if (var2) {
         this.flags |= 2;
      }

      if (var3) {
         this.flags |= 4;
      }
   }

   public void setDefaultAllow(boolean var1) {
      this.defaultAllow = var1;
   }

   public boolean isDefaultAllow() {
      return this.defaultAllow;
   }

   public void addRestricted(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.contains("->")) {
            if (var1.contains(":")) {
               this.restrictedFields.add(var1);
            } else {
               this.restrictedMethods.add(var1);
            }
         }

         this.restrictedClasses.add(var1);
      }
   }

   public void addWhitelisted(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.contains("->")) {
            if (var1.contains(":")) {
               this.whitelistedFields.add(var1);
            } else {
               this.whitelistedMethods.add(var1);
            }
         }

         this.whitelistedClasses.add(var1);
      }
   }

   public void addBlacklisted(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.contains("->")) {
            if (var1.contains(":")) {
               this.blacklistedFields.add(var1);
            } else {
               this.blacklistedMethods.add(var1);
            }
         }

         this.blacklistedClasses.add(var1);
      }
   }

   public boolean canExecute(String var1) {
      JvmMethodSig var2 = JvmMethodSig.parse(var1);
      if (this.blacklistedClasses.contains(var2.csig) || this.blacklistedMethods.contains(var1)) {
         return false;
      } else if (!this.whitelistedClasses.contains(var2.csig) && !this.whitelistedMethods.contains(var1)) {
         if ((this.flags & 1) != 0) {
            if (random_classes.contains(var2.csig)) {
               return false;
            }

            if (random_methods.contains(var1)) {
               return false;
            }
         }

         if ((this.flags & 2) != 0) {
            if (time_classes.contains(var2.csig)) {
               return false;
            }

            if (time_methods.contains(var1)) {
               return false;
            }
         }

         if ((this.flags & 4) != 0) {
            if (env_classes.contains(var2.csig)) {
               return false;
            }

            if (env_methods.contains(var1)) {
               return false;
            }
         }

         return !this.restrictedClasses.contains(var2.csig) && !this.restrictedMethods.contains(var1) ? this.defaultAllow : false;
      } else {
         return true;
      }
   }

   public boolean canAccess(String var1) {
      JvmFieldSig var2 = JvmFieldSig.parse(var1);
      if (this.blacklistedClasses.contains(var2.csig) || this.blacklistedFields.contains(var1)) {
         return false;
      } else if (!this.whitelistedClasses.contains(var2.csig) && !this.whitelistedFields.contains(var1)) {
         if ((this.flags & 1) != 0) {
            if (random_classes.contains(var2.csig)) {
               return false;
            }

            if (random_fields.contains(var1)) {
               return false;
            }
         }

         if ((this.flags & 2) != 0) {
            if (time_classes.contains(var2.csig)) {
               return false;
            }

            if (time_fields.contains(var1)) {
               return false;
            }
         }

         if ((this.flags & 4) != 0) {
            if (env_classes.contains(var2.csig)) {
               return false;
            }

            if (env_fields.contains(var1)) {
               return false;
            }
         }

         return !this.restrictedClasses.contains(var2.csig) && !this.restrictedFields.contains(var1) ? this.defaultAllow : false;
      } else {
         return true;
      }
   }

   static {
      random_classes.add("Ljava/util/concurrent/ThreadLocalRandom;");
      random_methods.add("Ljava/lang/Math;->random()D");
      random_methods.add("Ljava/lang/StrictMath;->random()D");
      random_methods.add("Ljava/util/Random;-><init>()V");
      random_methods.add("Ljava/util/SplittableRandom;-><init>()V");
      random_methods.add("Ljava/security/SecureRandom;-><init>()V");
      random_methods.add("Ljava/security/SecureRandom;-><init>(Ljava/security/SecureRandomSpi;Ljava/security/Provider;)V");
      time_methods.add("Ljava/lang/System;->currentTimeMillis()J");
      time_methods.add("Ljava/lang/System;->nanoTime()J");
      time_methods.add("Ljava/time/Clock;->system(Ljava/time/ZoneId;)Ljava/time/Clock;");
      time_methods.add("Ljava/time/Clock;->systemDefaultZone()Ljava/time/Clock;");
      time_methods.add("Ljava/time/Clock;->systemUTC()Ljava/time/Clock;");
      time_methods.add("Ljava/time/Instant;->now()Ljava/time/Instant;");
      time_methods.add("Ljava/time/LocalDate;->now()Ljava/time/LocalDate;");
      time_methods.add("Ljava/time/LocalDate;->now(Ljava/time/ZoneId;)Ljava/time/LocalDate;");
      time_methods.add("Ljava/time/LocalDateTime;->now()Ljava/time/LocalDateTime;");
      time_methods.add("Ljava/time/LocalDateTime;->now(Ljava/time/ZoneId;)Ljava/time/LocalDateTime;");
      time_methods.add("Ljava/time/LocalTime;->now()Ljava/time/LocalTime;");
      time_methods.add("Ljava/time/LocalTime;->now(Ljava/time/ZoneId;)Ljava/time/LocalTime;");
      time_methods.add("Ljava/time/MonthDay;->now()Ljava/time/MonthDay;");
      time_methods.add("Ljava/time/MonthDay;->now(Ljava/time/ZoneId;)Ljava/time/MonthDay;");
      time_methods.add("Ljava/time/OffsetDateTime;->now()Ljava/time/OffsetDateTime;");
      time_methods.add("Ljava/time/OffsetDateTime;->now(Ljava/time/ZoneId;)Ljava/time/OffsetDateTime;");
      time_methods.add("Ljava/time/OffsetTime;->now()Ljava/time/OffsetTime;");
      time_methods.add("Ljava/time/OffsetTime;->now(Ljava/time/ZoneId;)Ljava/time/OffsetTime;");
      time_methods.add("Ljava/time/Year;->now()Ljava/time/Year;");
      time_methods.add("Ljava/time/Year;->now(Ljava/time/ZoneId;)Ljava/time/Year;");
      time_methods.add("Ljava/time/YearMonth;->now()Ljava/time/YearMonth;");
      time_methods.add("Ljava/time/YearMonth;->now(Ljava/time/ZoneId;)Ljava/time/YearMonth;");
      time_methods.add("Ljava/time/ZonedDateTime;->now()Ljava/time/ZonedDateTime;");
      time_methods.add("Ljava/time/ZoneId;->systemDefault()Ljava/time/ZoneId;");
      time_methods.add("Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;");
      time_methods.add("Ljava/util/Calendar;->getInstance(Ljava/util/Locale;)Ljava/util/Calendar;");
      time_methods.add("Ljava/util/Calendar;->getInstance(Ljava/util/TimeZone;)Ljava/util/Calendar;");
      time_methods.add("Ljava/util/Calendar;->getInstance(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;");
      time_methods.add("Landroid/os/SystemClock;->currentGnssTimeClock()Ljava/time/Clock;");
      time_methods.add("Landroid/os/SystemClock;->currentThreadTimeMillis()L");
      time_methods.add("Landroid/os/SystemClock;->elapsedRealtime()L");
      time_methods.add("Landroid/os/SystemClock;->elapsedRealtimeNanos()L");
      time_methods.add("Landroid/os/SystemClock;->uptimeMillis()L");
      env_methods.add("Ljava/lang/System;->getenv()Ljava/util/Map;");
      env_methods.add("Ljava/lang/System;->getenv(Ljava/lang/String;)Ljava/lang/String;");
      env_methods.add("Ljava/lang/System;->getProperties()Ljava/util/Properties;");
      env_methods.add("Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;");
      env_methods.add("Ljava/lang/System;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
      env_classes.add("Ljava/net/NetworkInterface;");
      env_classes.add("Landroid/os/Environment;");
      env_classes.add("Landroid/os/Build;");
      env_classes.add("Landroid/os/Build$Partition;");
      env_classes.add("Landroid/os/Build$VERSION;");
      env_classes.add("Landroid/os/Build$VERSION_CODES;");
      env_methods.add("Ljava/util/Locale;->getDefault()Ljava/util/Locale;");
      env_methods.add("Ljava/util/Locale;->getDefault(Ljava/util/Locale$Category;)Ljava/util/Locale;");
   }
}
